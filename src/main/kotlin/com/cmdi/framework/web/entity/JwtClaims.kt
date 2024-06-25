package com.cmdi.framework.web.entity

data class JwtClaims(
    val uuid: String,
    val host: String,
    val userAgent: String
)
