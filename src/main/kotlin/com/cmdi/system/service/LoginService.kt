package com.cmdi.system.service

import com.cmdi.common.utils.JwtUtils
import com.cmdi.system.entity.LoginUser
import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.ext.web.RoutingContext
import jakarta.inject.Singleton
import java.util.*

@Singleton
class LoginService(
    private val route: RoutingContext,
) {

    fun login(loginUser: LoginUser): String {
        val uuid = UUID.randomUUID().toString()
        val host = route.request().remoteAddress().host()
        val userAgent = route.request().headers().get("User-Agent")
        if (loginUser.password!!.length < 10) {
            throw RuntimeException("登录失败！")
        }
        return JwtUtils.creatToken(
            ObjectMapper().writeValueAsString(
                mapOf(
                    "uuid" to uuid,
                    "host" to host,
                    "userAgent" to userAgent
                )
            )
        )
    }

}
