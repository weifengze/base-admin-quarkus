package com.cmdi.common.utils

import io.quarkus.redis.datasource.RedisDataSource
import io.quarkus.redis.datasource.value.SetArgs
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RedisUtils(
    private val redisDataSource: RedisDataSource,
) {


    /**
     * 缓存json数据
     *
     * @param key 缓存key
     * @param value 缓存value
     */
    fun setJsonObject(key: String, value: Any) {
        redisDataSource.value(Any::class.java).set(key, JsonUtils.obj2String(value))
    }

    /**
     * 缓存json数据，指定过期时间（秒）
     *
     * @param key 缓存key
     * @param value 缓存value
     * @param timeout 过期时间（秒）
     */
    fun setJsonObject(key: String, value: Any, timeout: Long) {
        redisDataSource.value(Any::class.java).set(key, JsonUtils.obj2String(value), SetArgs().ex(timeout))
    }

}
