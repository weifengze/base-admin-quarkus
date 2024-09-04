package com.rich.laken.common.utils

import com.rich.laken.common.exception.UnauthorizedException
import com.rich.laken.framework.web.entity.LoginUser
import com.rich.laken.framework.security.service.TokenService
import io.quarkus.arc.Arc
import org.jboss.resteasy.reactive.server.core.CurrentRequestManager
import org.jboss.resteasy.reactive.server.core.ResteasyReactiveRequestContext

/**
 * 登录用户
 *
 * @author weifengze
 */
object LoginUtils {
    private var context: ResteasyReactiveRequestContext? = CurrentRequestManager.get()

    /**
     * 获取登录用户
     */
    fun getLoginUser(): LoginUser? {
        val token = context?.getHeader("Authorization", true).toString()
        if (token.isNotEmpty()) {
            return Arc.container().instance(TokenService::class.java).get().getLoginUser(token)
        }
        throw UnauthorizedException()
    }

    /**
     * 获取用户账户
     */
    fun getUserName(): String = getLoginUser()?.user?.userName.toString()

    /**
     * 获取用户id
     */
    fun getUserId(): Long = getLoginUser()?.userId!!
}
