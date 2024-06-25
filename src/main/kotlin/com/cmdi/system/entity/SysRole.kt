package com.cmdi.system.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.*

/**
 * 角色
 *
 * @author weifengze
 */
interface SysRole : Entity<SysRole> {
    /**
     * 角色ID
     */
    var roleId: Long?

    /**
     * 角色名称
     */
    var roleName: String?

    /**
     * 角色权限
     */
    var roleKey: String?

    /**
     * 角色排序
     */
    var roleSort: Int?

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    var dataScope: String?

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    var menuCheckStrictly: Boolean

    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    var deptCheckStrictly: Boolean

    /**
     * 角色状态（0正常 1停用）
     */
    var status: Int?

    /**
     * 用户是否存在此角色标识
     * 默认不存在
     */
    var flag: Boolean

    /**
     * 菜单组
     */
    var menuIds: Array<Long>

    /**
     * 部门组（数据权限）
     */
    var deptIds: Array<Long>

    /**
     * 角色菜单权限
     */
    var permissions: Set<String>

    var childRole: SysRole?
}

object SysRoles : Table<SysRole>("sys_role") {
    val roleId = long("role_id").primaryKey().bindTo { it.roleId }
    val roleName = varchar("role_name").bindTo { it.roleName }
    val roleKey = varchar("role_key").bindTo { it.roleKey }
    val roleSort = int("role_sort").bindTo { it.roleSort }
    val dataScope = varchar("data_scope").bindTo { it.dataScope }
    val menuCheckStrictly = boolean("menu_check_strictly").bindTo { it.menuCheckStrictly }
    val deptCheckStrictly = boolean("dept_Check_Strictly").bindTo { it.deptCheckStrictly }
    val status = int("status").bindTo { it.status }
    val flag = boolean("flag").bindTo { it.flag }
}
