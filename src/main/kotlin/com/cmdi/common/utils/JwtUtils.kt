package com.cmdi.common.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys

class JwtUtils {

    companion object {
        /**
         * 创建一个JWT Token
         *
         * @param sub 提供Token的主体（subject），通常是用户ID
         * @param secretKey 密钥
         * @return 返回生成的Token字符串
         */
        fun builder(sub: String, secretKey: String): String {
            return Jwts.builder()
                .subject(sub)
                .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .compact()
                .toString()
        }

        /**
         * 解析 JWT Token
         *
         * @param token JWT Token
         * @param secretKey 密钥
         * @return sub 值
         */
        fun parse(token: String, secretKey: String): String {
            return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
                .toString()
        }
    }
}
