package com.cmdi.system.resource

import com.cmdi.framework.web.entity.R
import com.cmdi.system.entity.LoginUser
import com.cmdi.system.service.LoginService
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/login")
class LoginResource(
    private val loginService: LoginService,
) {

    /**
     * 登录
     *
     * @param loginUser 登录用户
     * @return R<Any>
     */
    @POST
    @Path("/")
    fun login(loginUser: LoginUser): R<Any> = R.ok(loginService.login(loginUser))
}
