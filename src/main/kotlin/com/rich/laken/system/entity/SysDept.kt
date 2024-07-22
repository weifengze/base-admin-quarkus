package com.rich.laken.system.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * 部门
 *
 * @author weifengze
 */
interface SysDept : Entity<SysDept> {

    companion object : Entity.Factory<SysDept>()

    /**
     * 部门ID
     */
    var deptId: Long?

    /**
     * 父部门ID
     */
    var parentId: Long?

    /**
     * 祖级列表
     */
    var ancestors: String?

    /**
     * 部门名称
     */
    var deptName: String?

    /**
     * 显示顺序
     */
    var orderNum: Int?

    /**
     * 负责人
     */
    var leader: String?

    /**
     * 联系电话
     */
    var phone: String?

    /**
     * 邮箱
     */
    var email: String?

    /**
     * 部门状态:0正常,1停用
     */
    var status: Int?

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    var delFlag: Int?

    /**
     * 父部门名称
     */
    var parentName: String?

    /**
     * 子部门
     */
    var children: List<SysDept>
}

object SysDepts : Table<SysDept>("sys_dept") {
    val deptId = long("dept_id").primaryKey().bindTo { it.deptId }
//    val parentId = long("parentId").bindTo { it.parentId }
    val ancestors = varchar("ancestors").bindTo { it.ancestors }
    val deptName = varchar("dept_name").bindTo { it.deptName }
    val orderNum = int("order_num").bindTo { it.orderNum }
    val leader = varchar("leader").bindTo { it.leader }
    val phone = varchar("phone").bindTo { it.phone }
    val email = varchar("email").bindTo { it.email }
    val status = int("status").bindTo { it.status }
    val delFlag = int("del_flag").bindTo { it.delFlag }
}
