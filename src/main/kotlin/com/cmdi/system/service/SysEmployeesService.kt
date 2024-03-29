package com.cmdi.system.service

import com.cmdi.system.entity.SysEmployee
import com.cmdi.system.mapper.SysEmployeesMapper
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

/**
 * 用户信息 Service
 *
 * @author weifengze
 */
@ApplicationScoped
class SysEmployeesService {

    @Inject
    private lateinit var employeesMapper: SysEmployeesMapper

    /**
     * 查询用户信息
     * @param sysEmployee SysEmployee
     * @return List<SysEmployee>
     */
    fun searchEmployeePage(sysEmployee: SysEmployee): PageInfo<SysEmployee> {
        PageHelper.startPage<Any>(sysEmployee.pageNum, sysEmployee.pageSize)
        return PageInfo(employeesMapper.searchEmployeeList(sysEmployee))
    }

}
