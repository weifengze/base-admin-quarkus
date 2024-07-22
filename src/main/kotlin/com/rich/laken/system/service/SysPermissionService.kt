package com.rich.laken.system.service

import com.rich.laken.system.entity.SysMenu
import com.rich.laken.system.entity.SysUser
import jakarta.inject.Singleton

/**
 * 用户权限 service
 *
 * @author weifengze
 */
@Singleton
class SysPermissionService(
    private val menuService: SysMenuService
) {

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    fun getMenuPermission(user: SysUser): Set<String> {
        val perms = mutableSetOf<String>()
//
//        // 定义一个获取权限的高阶函数
//        val getPerms: (Long) -> Set<String> = { roleId ->
//            menuService.selectMenuPermsByRoleId(roleId)
//        }
//
//        if (user.userId == 1L) {
//            // 管理员拥有所有权限
//            perms.add("*:*:*")
//        } else {
//            user.roles.takeIf { it.isNotEmpty() }?.let { roles ->
//                if (roles.size > 1) {
//                    // 多角色设置permissions属性，以便数据权限匹配权限
//                    roles.forEach { role ->
//                        val rolePerms = getPerms(role.roleId)
//                        role.permissions = rolePerms
//                        perms.addAll(rolePerms)
//                    }
//                } else {
//                    // 只有一个角色时，直接获取用户权限
//                    perms.addAll(menuService.selectMenuPermsByUserId(user.userId))
//                }
//            }
//        }
//
        return perms
    }
}
