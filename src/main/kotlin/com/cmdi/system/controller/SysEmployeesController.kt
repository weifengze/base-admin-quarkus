package com.cmdi.system.controller

import com.cmdi.framework.web.entity.R
import com.cmdi.system.entity.SysEmployee
import com.cmdi.system.service.SysEmployeesService
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/sysmanage/employee")
class SysEmployeesController(
    private val employeesService: SysEmployeesService,
) {

    @POST
    @Path("/searchEmployeePage")
    @Produces(MediaType.APPLICATION_JSON)
    fun searchEmployeePage(sysEmployee: SysEmployee): R<SysEmployee> {
        return R.table(employeesService.searchEmployeePage(sysEmployee))
    }

}
