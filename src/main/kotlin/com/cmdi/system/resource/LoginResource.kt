package com.cmdi.system.resource

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/login")
class LoginResource(

) {

    @POST
    @Path("/")
    fun login() {
        if (1 == 1) {
            throw RuntimeException("登录失败！")
        }
    }
}
