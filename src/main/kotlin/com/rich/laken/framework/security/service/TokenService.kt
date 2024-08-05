package com.rich.laken.framework.security.service

import com.rich.laken.common.utils.JsonUtils
import com.rich.laken.common.utils.JwtUtils
import com.rich.laken.common.utils.RedisUtils
import com.rich.laken.framework.web.entity.JwtClaims
import com.rich.laken.framework.web.entity.LoginUser
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
        private const val MILLIS_MINUTE_TEN: Long = 10 * 60 * 1000L
    }

    /**
     * 创建jwt令牌
     *
     * @param loginUser 登录用户信息
     * @return String 令牌
     */
    fun createToken(loginUser: LoginUser): String {
        refreshToken(loginUser)
        val subject = JsonUtils.obj2String(
            JwtClaims(
                uuid = loginUser.token!!,
                host = loginUser.ipaddr!!,
                userAgent = loginUser.browser!!
            )
        )
        return JwtUtils.builder(subject!!, secret)
    }


    /**
     * 解析token
     *
     * @param token JWT Token
     * @return JwtClaims
     */
    fun parseToken(jwtToken: String): JwtClaims? =
        JsonUtils.parseObject(JwtUtils.parse(jwtToken, secret), JwtClaims::class.java)


    /**
     * 验证令牌有效期，相差不足10分钟，自动刷新缓存
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    fun verifyToken(loginUser: LoginUser) {
        val expireTime = loginUser.expireTime!!
        val currentTime = System.currentTimeMillis()
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser)
        }
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

    /**
     * 获取用户信息
     *
     * @param jwtToken 缓存key
     * @return LoginUser
     */
    fun getLoginUser(jwtToken: String): LoginUser? {
        val jwtClaims = parseToken(jwtToken)!!
        val cacheKey = getTokenKey(jwtClaims.uuid)
        return redisUtils.getJsonObject(cacheKey, LoginUser::class.java)
    }


    private fun getTokenKey(uuid: String?): String = "quarkus:login_tokens:${uuid}"
}
