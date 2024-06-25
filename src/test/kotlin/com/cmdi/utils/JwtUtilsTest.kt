package com.cmdi.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

@QuarkusTest
class JwtUtilsTest {
    private val secretKey = "44t0LF6AxBJrzEHYYgffQat1yLekguVe5"

    @Test
    fun builder() {
        val claims = JwtClaims(
            "12L",
            "127.0.0.1",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
        )
        val expirationDate = Date(Date().time + 30 * 60 * 1000)
        val compact = Jwts.builder()
            .subject(ObjectMapper().writeValueAsString(claims)) // 设置主体（payload 中的数据）
            .id(UUID.randomUUID().toString()) // 可选：设置 JWT ID
            .issuedAt(Date()) // 设置签发时间
            .expiration(expirationDate) // 设置过期时间
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))// 设置签名算法及密钥
            .compact() // 生成最终的 JWT 字符串
        println(compact)
        println(compact.toString())
    }

    @Test
    fun parse() {
        val objectMapper = ObjectMapper()
        val jwt =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjpcIjEyTFwiLFwiaG9zdFwiOlwiMTI3LjAuMC4xXCIsXCJ1c2VyQWdlbnRcIjpcIk1vemlsbGEvNS4wIChXaW5kb3dzIE5UIDEwLjA7IFdpbjY0OyB4NjQpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS85MS4wLjQ0NzIuMTI0IFNhZmFyaS81MzcuMzZcIn0iLCJqdGkiOiJjNzgzYWQ4NS00YjRjLTQ1NzQtYjY5MC01NGY4NTk1MjAxYTAiLCJpYXQiOjE3MTIzMzMzMzksImV4cCI6MTcxMjMzNTEzOX0.aF5A6xTb4dTzZ1r4u2wycZyqCqaHIy4Xh8h5FNJCwuc"
        val hmacShaKeyFor = Keys.hmacShaKeyFor(secretKey.toByteArray())
        val claims = Jwts.parser()
            .verifyWith(hmacShaKeyFor)
            .build()
            .parseSignedClaims(jwt)
            .payload
        val jwtClaims = objectMapper.readValue(claims.subject.toString(), object : TypeReference<JwtClaims>() {})
        println(jwtClaims.host)
    }

    @Test
    fun uuid() {
        println(UUID.randomUUID().toString().replace("-",""))
    }
}

data class JwtClaims(val userId: String, val host: String, val userAgent: String) {
    constructor() : this("", "", "")
}
