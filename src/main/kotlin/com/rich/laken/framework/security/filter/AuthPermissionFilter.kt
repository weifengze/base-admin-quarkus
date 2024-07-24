package com.rich.laken.framework.security.filter

import com.rich.laken.common.constants.Constant
import com.rich.laken.common.exception.UnauthorizedException
import com.rich.laken.framework.annotation.AuthPermission
import com.rich.laken.framework.security.service.TokenService
import jakarta.annotation.Priority
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Priorities
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ResourceInfo
import jakarta.ws.rs.ext.Provider
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

/**
 * 操作权限过滤器
 */
@ApplicationScoped
@Priority(Priorities.USER + 300)
@Provider
class AuthPermissionFilter(
    private val resourceInfo: ResourceInfo,
    private val tokenService: TokenService,
) : ContainerRequestFilter {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    companion object {
        /**
         * 所有权限标识
         */
        private const val ALL_PERMISSION = "*:*:*"
    }

    override fun filter(requestContext: ContainerRequestContext) {
        logger.debug("AuthPermissionFilter: {}", requestContext.uriInfo.requestUri)
        logger.debug("get the contents of the request method annotation：{}", authPermission())
        val jwtToken = requestContext.headers["Authorization"]?.firstOrNull()
        if (!jwtToken.isNullOrEmpty()) {
            val loginUser = tokenService.getLoginUser(jwtToken)
            // 超级管理员直接跳过验证权限
            if (loginUser?.user?.userId != Constant.SUPER_ADMIN_ID) {
                val permissions = loginUser?.permissions
                if (!permissions.isNullOrEmpty()) {
                    if (!hasPermissions(permissions, authPermission())) {
                        throw UnauthorizedException()
                    }
                } else {
                    throw UnauthorizedException()
                }
            }
        }
    }

    /**
     * 获取请求方法注解的内容
     */
    private fun authPermission(): String {
        val method = resourceInfo.resourceMethod
        val resourceClass = resourceInfo.resourceClass

        val preAuthorize =
            method.getAnnotation(AuthPermission::class.java) ?: resourceClass.getAnnotation(AuthPermission::class.java)

        return preAuthorize?.value ?: StringUtils.EMPTY
    }

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    fun hasPermission(permissions: Set<String>?, permission: String?): Boolean {
        return if (permissions.isNullOrEmpty()) {
            false
        } else {
            hasPermissions(permissions, permission)
        }
    }

    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private fun hasPermissions(permissions: Set<String>, permission: String?): Boolean =
        permissions.contains(ALL_PERMISSION) || permission?.trim()?.let { permissions.contains(it) } == true

}
