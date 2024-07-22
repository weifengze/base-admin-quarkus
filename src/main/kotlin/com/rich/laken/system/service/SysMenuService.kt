package com.rich.laken.system.service

import jakarta.inject.Singleton

/**
 * 菜单 service
 *
 * @author weifengze
 */
@Singleton
class SysMenuService(
//    private val menuMapper: SysMenuMapper,
) {

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    fun selectMenuPermsByRoleId(roleId: Long): Set<String> {
        TODO()
    }

}
