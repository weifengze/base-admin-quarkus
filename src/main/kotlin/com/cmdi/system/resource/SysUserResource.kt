package com.cmdi.system.resource

import com.cmdi.system.entity.SysUser
import com.cmdi.system.service.SysUserService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/system/user")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class SysUserResource(
    private val userService: SysUserService,
) {


    @Path("/list")
    @GET
    fun selectSysUserList(user: SysUser?): List<SysUser> {
        return userService.selectUserList(user)
    }

    @Path("/search")
    @GET
    fun search1(@QueryParam("name") name: String) {
        println("name: $name")
    }

    @Path("/search")
    @POST
    fun search2(user: SysUser) {
        println("name: ${user.userName}")
    }

}
