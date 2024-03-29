package com.cmdi.system.entity

import com.cmdi.framework.web.entity.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SysEmployee(

    /**
     *
     */
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var employeeId: Long? = null,

    /**
     *
     */
    var empAccount: String? = null,

    /**
     *
     */
    var name: String? = null,

    /**
     * 自动开通系统角色code，英文逗号分割，提前约定
     */
    var initialRole: String? = null,

    /**
     *
     */
    var email: String? = null,

    /**
     *
     */
    var orgId: Long? = null,

    /**
     *
     */
    var orgFullId: String? = null,

    /**
     *
     */
    var orgFullName: String? = null,

    /**
     *
     */
    var businessScope: String? = null,

    /**
     * 0-无效 1-有效
     */
    var empStatus: Boolean? = null,

    /**
     *
     */
    var level: Int? = null,

    /**
     *
     */
    var phone: String? = null,

    /**
     *
     */
    var posName: String? = null,

    /**
     * 0-女 1-男
     */
    var sex: Int? = null,

    /**
     *
     */
    var userCode: String? = null,

    /**
     * 1-有效 5-禁用
     */
    var workState: Int? = null,

    /**
     *
     */
    @Contextual var createDate: Date? = null,

    /**
     *
     */
    var createUser: Long? = null,

    /**
     *
     */
    @Contextual var upDate: Date? = null,

    /**
     *
     */
    var upUser: Long? = null,

    /**
     * 用户类型（00内部用户，01外部用户）
     */
    var userType: String? = null,
) : BaseEntity()
