package com.rich.laken.framework.security.service

import com.rich.laken.common.utils.JsonUtils
import com.rich.laken.common.utils.JwtUtils
import com.rich.laken.common.utils.RedisUtils
import com.rich.laken.framework.entity.JwtClaims
import com.rich.laken.framework.entity.LoginUser
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty

/**
 * 令牌服务
 *
 * @author weifengze
 */
@ApplicationScoped
class TokenService(
    /**
     * 令牌自定义标识
     */
    @ConfigProperty(name = "token.header")
    var header: String,
    /**
     * 密钥
     */
    @ConfigProperty(name = "token.secret")
    var secret: String,

    /**
     * 过期时间
     */
    @ConfigProperty(name = "token.expire-time")
    var expireTime: Int,
) {

    @Inject
    private lateinit var redisUtils: RedisUtils

    companion object {
        private const val MILLIS_SECOND: Long = 1000
        private const val MINUTE: Long = 60
        private const val MILLIS_MINUTE: Long = MINUTE * MILLIS_SECOND
        private const val MILLIS_MINUTE_TEN = 20 * 60 * 1000L
    }

    /**
     * 创建jwt令牌
     *
     * @param loginUser 登录用户信息
     * @return String 令牌
     */
    fun createToken(loginUser: LoginUser): String {
        refreshToken(loginUser)
        val subject = JsonUtils.obj2String(JwtClaims(loginUser.token!!, loginUser.ipaddr!!, loginUser.browser!!))
        return JwtUtils.builder(subject!!, secret)
    }


    /**
     * 解析token
     *
     * @param token JWT Token
     * @return JwtClaims
     */
    fun parseToken(token: String): JwtClaims? {
        return JsonUtils.parseObject(JwtUtils.parse(token, secret), JwtClaims::class.java)
    }


    /**
     * 刷新 jwt 令牌
     *
     * @param loginUser 登录用户信息
     */
    fun refreshToken(loginUser: LoginUser) {
        val currentTimeMillis = System.currentTimeMillis()
        loginUser.loginTime = currentTimeMillis
        loginUser.expireTime = currentTimeMillis + expireTime * MILLIS_MINUTE
        redisUtils.setJsonObject(getTokenKey(loginUser.token), loginUser, expireTime * MINUTE)
    }


    private fun getTokenKey(uuid: String?): String = "quarkus:login_tokens:${uuid}"
}
