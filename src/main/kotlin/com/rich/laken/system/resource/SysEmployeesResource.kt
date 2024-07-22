package com.rich.laken.system.resource

import com.rich.laken.framework.entity.R
import com.rich.laken.system.service.SysEmployeesService
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/sysmanage/employee")
class SysEmployeesResource(
    private val employeesService: SysEmployeesService,
) {

//    @POST
//    @Path("/searchEmployeePage")
//    fun searchEmployeePage(sysEmployee: SysEmployee): R<SysEmployee> {
//        return R.table(employeesService.searchEmployeePage(sysEmployee))
//    }
//
//
//    @POST
//    @Path("/findEmployeeInfo")
//    fun findEmployeeInfo(recoderId: Long): R<SysEmployee> {
//        if (recoderId >= 0) {
//            return R.fail("Invalid recoderId: $recoderId")
//        }
//        return R.ok(employeesService.findEmployeeInfo(recoderId))
//    }

}
