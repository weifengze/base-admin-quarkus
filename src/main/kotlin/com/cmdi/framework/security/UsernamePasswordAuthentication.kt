package com.cmdi.framework.security

import de.mkammerer.argon2.Argon2Factory

/**
 * 用户名密码身份验证
 *
 * @author weifengze
 */
class UsernamePasswordAuthentication {

    companion object {
        /**
         * 使用 Argon2 的 ARGON2id 模式
         */
        private val argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64)


        /**
         * 生成密码
         *
         * @param password 密码
         * @param salt 盐
         * @return String 加密后的密码
         */
        fun generateHashPassword(password: String, salt: String): String {
            return argon2.hash(23, 65536, 1, (password + salt).toByteArray())
        }

        /**
         * 验证密码
         *
         * @param hashPassword argon2加密的密码
         * @param password 原密码
         * @return Boolean
         */
        fun verifyPassword(hashPassword: String, password: String): Boolean {
            return argon2.verify(hashPassword, password.toByteArray())
        }
    }

}
