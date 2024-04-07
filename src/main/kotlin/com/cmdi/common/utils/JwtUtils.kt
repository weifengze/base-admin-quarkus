package com.cmdi.common.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*

class JwtUtils {

    companion object {
        private const val SECRET_KEY = "44t0LF6AxBJrzEHYYgffQat1yLekguVe5"

        /**
         * 创建一个JWT Token
         *
         * @param sub 提供Token的主体（subject），通常是用户ID
         * @return 返回生成的Token字符串
         */
        fun creatToken(sub: String): String {
            // 设置Token的过期时间为当前时间后30分钟
            val expirationDate = Date(Date().time + 30 * 60 * 1000)
            return Jwts.builder()
                .subject(sub)
                .issuedAt(Date())
                .expiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
                .compact()
                .toString()
        }

        fun parseToken(token: String): String {
            return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
                .toString()
        }
    }
}
