package com.cmdi.system.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.cmdi.framework.web.entity.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

@TableName("sys_employee_info")
data class SysEmployee(

    /**
     *
     */
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("employee_id")
    var employeeId: Long? = null,

    /**
     *
     */
    @TableField("emp_account")
    var empAccount: String? = null,

    /**
     *
     */
    @TableField("name")
    var name: String? = null,

    /**
     * 自动开通系统角色code，英文逗号分割，提前约定
     */
    @TableField("initial_role")
    var initialRole: String? = null,

    /**
     *
     */
    @TableField("email")
    var email: String? = null,

    /**
     *
     */
    @TableField("org_id")
    var orgId: Long? = null,

    /**
     *
     */
    @TableField("org_full_id")
    var orgFullId: String? = null,

    /**
     *
     */
    @TableField("org_full_name")
    var orgFullName: String? = null,

    /**
     *
     */
    @TableField("business_scope")
    var businessScope: String? = null,

    /**
     * 0-无效 1-有效
     */
    @TableField("emp_status")
    var empStatus: Boolean? = null,

    /**
     *
     */
    @TableField("level")
    var level: Int? = null,

    /**
     *
     */
    @TableField("phone")
    var phone: String? = null,

    /**
     *
     */
    @TableField("pos_name")
    var posName: String? = null,

    /**
     * 0-女 1-男
     */
    @TableField("sex")
    var sex: Int? = null,

    /**
     *
     */
    @TableField("user_code")
    var userCode: String? = null,

    /**
     * 1-有效 5-禁用
     */
    @TableField("work_state")
    var workState: Int? = null,

    /**
     *
     */
    @TableField("create_date")
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createDate: Date? = null,

    /**
     *
     */
    @TableField("create_user")
    var createUser: Long? = null,

    /**
     *
     */
    @TableField("up_date")
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updateDate: Date? = null,

    /**
     *
     */
    @TableField("up_user")
    var updateUser: Long? = null,

    /**
     * 用户类型（00内部用户，01外部用户）
     */
    @TableField("user_type")
    var userType: String? = null,
) : BaseEntity()
