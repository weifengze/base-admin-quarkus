package com.rich.laken.framework.entity

data class JwtClaims(
    val uuid: String,
    val host: String,
    val userAgent: String
)
