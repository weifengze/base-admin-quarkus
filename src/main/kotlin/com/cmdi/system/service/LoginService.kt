package com.cmdi.system.service

import com.cmdi.common.enums.UserStatus
import com.cmdi.common.exception.LoginException
import com.cmdi.common.utils.BrowserDetector
import com.cmdi.common.utils.IdUtils
import com.cmdi.framework.security.UsernamePasswordAuthentication
import com.cmdi.framework.security.service.TokenService
import com.cmdi.framework.web.entity.LoginBody
import com.cmdi.framework.web.entity.LoginUser
import com.cmdi.system.entity.SysUser
import com.cmdi.system.repository.SysUserRepository
import io.vertx.ext.web.RoutingContext
import jakarta.inject.Singleton
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.slf4j.LoggerFactory

/**
 * 登录认证 service
 *
 * @author weifengze
 */
@Singleton
class LoginService(
    private val route: RoutingContext,
    private val userRepository: SysUserRepository,
    private val tokenService: TokenService,
    private val permissionService: SysPermissionService,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * 登录验证
     *
     * @param loginBody 登录用户
     * @return 结果
     */
    fun login(loginBody: LoginBody): String {
        if (loginBody.password?.length!! < 10) {
            logger.error("密码不匹配，长度不足。")
            throw LoginException("登录失败，账号和密码不正确！")
        }

        val user = findByUsername(loginBody.username!!)

        when {
            user == null -> {
                logger.error("登录失败，登录用户：${loginBody.username}不存在！")
                throw LoginException("登录失败，账号和密码不正确！")
            }

            user.status == UserStatus.DISABLE.code -> {
                logger.error("登录失败，登录用户：${loginBody.username}已被停用！")
                throw LoginException("登录失败，账号已被停用！")
            }

            !validatePassword(user.password!!, loginBody) -> {
                logger.error("登录失败，登录用户：${loginBody.username}密码错误！")
                throw LoginException("登录失败，账号和密码不正确！")
            }

            else -> {
                return createToken(user)
            }
        }
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    private fun findByUsername(username: String): SysUser? {
        return userRepository.findOne { it.userName eq username and (it.delFlag eq 0) }


        /*userMapper.selectOne(
            KtQueryWrapper(SysUser::class.java)
                .eq(SysUser::userName, username)
                .eq(SysUser::delFlag, "0")
        )*/
    }

    /**
     * 验证密码
     *
     * @param hashPassword 数据库查询密码
     * @param loginBody 登录用户
     */
    private fun validatePassword(hashPassword: String, loginBody: LoginBody): Boolean =
        UsernamePasswordAuthentication.verifyPassword(hashPassword, loginBody.password + loginBody.username)


    /**
     * 生成 jwt token
     *
     * @param sysUser 用户
     * @return String token
     */
    private fun createToken(sysUser: SysUser): String {
        val uuid = IdUtils.randomSimpleUUID()
        val host = route.request().remoteAddress().host()
        val userAgent = route.request().headers().get("User-Agent")
        val detectBrowser = BrowserDetector.detectBrowser(userAgent)
        val loginUser = LoginUser().apply {
            token = uuid
            ipaddr = host
            browser = detectBrowser.name
            os = "Windows"
            user = sysUser.apply { password = "" }
            permissions = permissionService.getMenuPermission(sysUser)
        }
        return tokenService.createToken(loginUser)
    }

}
