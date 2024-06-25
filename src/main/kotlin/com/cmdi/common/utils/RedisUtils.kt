package com.cmdi.common.utils

import io.quarkus.redis.datasource.RedisDataSource
import io.quarkus.redis.datasource.value.SetArgs
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class RedisUtils {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Inject
    private lateinit var redisDataSource: RedisDataSource

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
     * @param timeout 过期时间（毫秒）
     */
    fun setJsonObject(key: String, value: Any, timeout: Long) {
        val setArgs = SetArgs().ex(timeout)
        redisDataSource.value(Any::class.java).set(key, JsonUtils.obj2String(value), setArgs)
    }

    /**
     * 缓存数据
     *
     * @param key 缓存key
     * @param value 缓存value
     * @param timeout 过期时间（秒）
     */
    fun <K, V> setCacheObject(key: K, value: V, valueType: Class<V>, timeout: Long) {
        val setArgs = SetArgs().ex(timeout)
        redisDataSource.value(valueType).set(key.toString(), value, setArgs)
    }

    /**
     * 缓存数据
     *
     * @param key 缓存key
     * @param value 缓存value
     */
    fun <K, V> setCacheObject(key: K, value: V, valueType: Class<V>) {
        redisDataSource.value(valueType).set(key.toString(), value)
    }
}
