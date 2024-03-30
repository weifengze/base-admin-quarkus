package com.cmdi.system.service

import com.baomidou.mybatisplus.core.toolkit.StringUtils
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.cmdi.system.entity.SysEmployee
import com.cmdi.system.mapper.SysEmployeesMapper
import jakarta.inject.Inject
import jakarta.inject.Singleton

/**
 * 用户信息 Service
 *
 * @author weifengze
 */
@Singleton
class SysEmployeesService {

    @Inject
    private lateinit var employeesMapper: SysEmployeesMapper

    /**
     * 查询用户信息
     * @param sysEmployee SysEmployee
     * @return List<SysEmployee>
     */
    fun searchEmployeePage(sysEmployee: SysEmployee): Page<SysEmployee> {
        return employeesMapper.selectPage(
            Page(sysEmployee.pageNum, sysEmployee.pageSize),
            KtQueryWrapper(SysEmployee::class.java)
                .like(StringUtils.isNotBlank(sysEmployee.name), SysEmployee::name, sysEmployee.name)
                .eq(StringUtils.isNotBlank(sysEmployee.empAccount), SysEmployee::empAccount, sysEmployee.empAccount)
                .eq(SysEmployee::workState, 1)
                .orderByAsc(SysEmployee::employeeId)
        )
    }

}
