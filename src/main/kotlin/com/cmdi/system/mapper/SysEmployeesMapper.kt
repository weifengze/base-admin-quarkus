package com.cmdi.system.mapper

import com.cmdi.system.entity.SysEmployee
import org.apache.ibatis.annotations.CacheNamespace
import org.apache.ibatis.annotations.Mapper

/**
 * 用户信息 Mapper
 *
 * @author weifengze
 */
@Mapper
@CacheNamespace(readWrite = false)
interface SysEmployeesMapper {

    /**
     * 查询用户信息
     * @param sysEmployee SysEmployee
     * @return List<SysEmployee>
     */
    fun searchEmployeeList(sysEmployee: SysEmployee): List<SysEmployee>
}
