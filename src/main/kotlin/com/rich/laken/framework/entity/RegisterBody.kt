package com.rich.laken.framework.entity

data class RegisterBody(
    /**
     * 账号/用户名
     */
    var username: String? = null,
    /**
     * 密码
     */
    var password: String? = null,
    /**
     * 验证码
     */
    var code: String? = null,
    /**
     * 唯一标识
     */
    var uuid: String? = null,
)

