package com.cmdi.system.resource

import com.cmdi.framework.web.entity.LoginBody
import com.cmdi.framework.web.entity.R
import com.cmdi.system.service.LoginService
import jakarta.validation.Valid
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

/**
 * 登录认证
 *
 * @author weifengze
 */
@Path("/")
class LoginResource(
    private val loginService: LoginService,
) {

    /**
     * 登录
     *
     * @param loginBody 登录用户
     * @return R<Any>
     */
    @POST
    @Path("/login")
    fun login(@Valid loginBody: LoginBody): R<Any> = R.ok(loginService.login(loginBody), "登录成功")
}
