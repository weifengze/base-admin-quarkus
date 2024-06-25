package com.cmdi.common.utils

import com.cmdi.framework.web.entity.LoginUser
import io.quarkus.redis.datasource.RedisDataSource
import io.quarkus.redis.datasource.value.SetArgs
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class RedisUtilsTest {

    @Inject
    private lateinit var redisDataSource: RedisDataSource

    @Inject
    private lateinit var redisUtils: RedisUtils

    private val key = "quarkus:test:"

    @Test
    fun setCacheObject() {
        val loginUser = LoginUser().apply {
            token = IdUtils.randomUUID()
            userId = 1
            deptId = 1
            ipaddr = "127.0.0.1"
            browser = "Microsoft Edge 113"
            os = "Windows 10"
        }
        println(redisUtils)
        redisUtils.setJsonObject("quarkus:login_tokens:b3885adac3ce4c95a97bf41ee22f349b", loginUser, 1800)
//        redisDataSource.value(LoginUser::class.java).set("quarkus:login_tokens:b3885adac3ce4c95a97bf41ee22f349a", loginUser)
    }

    @Test
    fun getCacheObject() {
        val loginUser = redisDataSource.value(LoginUser::class.java).get("${key}loginUser")
        println(loginUser)
    }
}
