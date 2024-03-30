package com.cmdi.system.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.cmdi.system.entity.SysEmployee
import org.apache.ibatis.annotations.Mapper

/**
 * 用户信息 Mapper
 *
 * @author weifengze
 */
@Mapper
interface SysEmployeesMapper : BaseMapper<SysEmployee> {

//    /**
//     * 查询用户信息
//     * @param sysEmployee SysEmployee
//     * @return List<SysEmployee>
//     */
//    fun searchEmployeeList(sysEmployee: SysEmployee): List<SysEmployee>
}
