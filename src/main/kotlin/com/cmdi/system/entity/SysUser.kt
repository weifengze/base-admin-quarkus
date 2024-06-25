package com.cmdi.system.entity

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDateTime

/**
 * 用户
 *
 * @author weifengze
 */
interface SysUser : Entity<SysUser> {

    companion object : Entity.Factory<SysUser>()

    /**
     * 用户ID
     */
    var userId: Long?

    /**
     * 部门ID
     */
    var deptId: Long?

    /**
     * 用户账号
     */
    var userName: String?

    /**
     * 用户昵称
     */
    var nickName: String?

    /**
     * 用户邮箱
     */
    var email: String?

    /**
     * 手机号码
     */
    var phonenumber: String?

    /**
     * 用户性别
     */
    var sex: Int

    /**
     * 用户头像
     */
    var avatar: String?

    /**
     * 密码
     */
    var password: String?

    /**
     * 帐号状态（0正常 1停用）
     */
    var status: Int?

    var delFlag: Int?

    /**
     * 最后登录IP
     */
    var loginIp: String?

    /**
     * 最后登录时间
     */
    var loginDate: LocalDateTime?

    /**
     * 部门
     */
    var dept: SysDept?

    /**
     * 角色
     */
    var roles: List<SysRole>?

    /**
     * 角色组
     */
    var roleIds: Array<Long>?

    /**
     * 岗位组
     */
    var postIds: Array<Long>?

    /**
     * 角色
     */
    var roleId: Long?
}

object SysUsers : Table<SysUser>("sys_user") {
    val userId = long("user_id").primaryKey().bindTo { it.userId }
    val deptId = long("dept_id").references(SysDepts) { it.dept }.bindTo { it.deptId }
    val userName = varchar("user_name").bindTo { it.userName }
    val nickName = varchar("nick_name").bindTo { it.nickName }
    val email = varchar("email").bindTo { it.email }
    val phonenumber = varchar("phonenumber").bindTo { it.phonenumber }
    val sex = int("sex").bindTo { it.sex }
    val avatar = varchar("avatar").bindTo { it.avatar }
    val password = varchar("password").bindTo { it.password }
    val status = int("status").bindTo { it.status }
    val delFlag = int("del_flag").bindTo { it.delFlag }
    val loginIp = varchar("login_ip").bindTo { it.loginIp }
    val loginDate = datetime("login_date").bindTo { it.loginDate }
}

val Database.sysUsers get() = this.sequenceOf(SysUsers)
