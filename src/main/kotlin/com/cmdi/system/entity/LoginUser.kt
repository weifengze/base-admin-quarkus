package com.cmdi.system.entity

import jakarta.validation.constraints.NotBlank

data class LoginUser(
    val username: String? = null,
    val password: String? = null,
    val userAgent: String? = null,
    val remoteHost: String? = null,
)
