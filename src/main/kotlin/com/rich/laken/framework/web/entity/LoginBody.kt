package com.rich.laken.framework.web.entity

import jakarta.validation.constraints.NotBlank

/**
 * 用户登录对象
 *
 * @author weifengze
 */
data class LoginBody(
    /**
     * 账号/用户名
     */
    @field:NotBlank(message = "账号不能为空")
    var username: String? = null,
    /**
     * 密码
     */
    @field:NotBlank(message = "密码不能为空")
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
