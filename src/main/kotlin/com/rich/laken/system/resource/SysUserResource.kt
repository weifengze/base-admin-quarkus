package com.rich.laken.system.resource

import com.rich.laken.framework.annotation.AuthPermission
import com.rich.laken.system.entity.SysUser
import com.rich.laken.system.service.SysUserService
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
    @AuthPermission("system:user:search")
    fun search1(@QueryParam("name") name: String) {
        println("name: $name")
    }

    @Path("/search")
    @POST
    fun search2(user: SysUser) {
        println("name: ${user.userName}")
    }

}
