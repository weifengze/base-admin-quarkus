package com.cmdi.system.controller

import com.cmdi.framework.web.entity.R
import com.cmdi.system.entity.SysEmployee
import com.cmdi.system.service.SysEmployeesService
import com.github.pagehelper.PageInfo
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/sysmanage/employee")
class SysEmployeesController(
    private val employeesService: SysEmployeesService,
) {

    @POST
    @Path("/searchEmployeePage")
    fun searchEmployeePage(sysEmployee: SysEmployee): R<PageInfo<SysEmployee>> {
        return R.ok(employeesService.searchEmployeePage(sysEmployee))
    }

}
