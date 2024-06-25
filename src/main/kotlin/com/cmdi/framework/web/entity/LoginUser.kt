package com.cmdi.framework.web.entity

import com.cmdi.system.entity.SysUser

/**
 * 登录用户身份权限
 *
 * @author weifengze
 */
data class LoginUser(

    var userId: Long? = null,

    var deptId: Long? = null,

    var token: String? = null,

    var loginTime: Long? = null,

    var expireTime: Long? = null,

    var ipaddr: String? = null,

    var loginLocation: String? = null,

    var browser: String? = null,

    var os: String? = null,

    var permissions: Set<String>? = emptySet(),

    var user: SysUser = SysUser(),
)
