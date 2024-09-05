package com.rich.laken.framework.handler

import com.rich.laken.common.enums.LimitType
import com.rich.laken.framework.annotation.RateLimiter
import io.quarkus.redis.datasource.RedisDataSource
import io.vertx.ext.web.RoutingContext
import io.vertx.redis.client.Command
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ResourceInfo
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import org.slf4j.LoggerFactory

@ApplicationScoped
@Provider
class RateLimiterHandler(
    private val redisDataSource: RedisDataSource,
    private val resourceInfo: ResourceInfo,
    private val route: RoutingContext,
) : ContainerRequestFilter {
    private val logger = LoggerFactory.getLogger(this.javaClass)


    override fun filter(requestContext: ContainerRequestContext) {
        val rateLimiter = rateLimiter()
        if (rateLimiter != null) {
            val combineKey = combineKey(rateLimiter)
            val count = rateLimiter.count
            val time = rateLimiter.time

            try {
                val number =
                    redisDataSource.execute(
                        Command.EVAL,
                        *arrayOf(redisScript(), "0", combineKey, count.toString(), time.toString())
                    ).toLong()
                if (number == null || number > count) {
                    abortWith(requestContext)
                }
                logger.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number, combineKey);
            } catch (e: Exception) {
                abortWith(requestContext)
                logger.error(e.message)
            }
        }
    }

    private fun abortWith(requestContext: ContainerRequestContext) {
        requestContext.abortWith(
            Response.status(Response.Status.FORBIDDEN)
                .entity(mapOf("code" to 500, "msg" to "访问过于频繁，请稍候再试"))
                .build()
        )
    }

    private fun redisScript(): String {
        return """
            local key = ARGV[1]
            local count = tonumber(ARGV[2])
            local time = tonumber(ARGV[3])
            local current = redis.call('get', key);
            if current and tonumber(current) > count then
                return tonumber(current);
            end
            current = redis.call('incr', key)
            if tonumber(current) == 1 then
                redis.call('expire', key, time)
            end
            return tonumber(current);
        """.trimIndent()
    }


    /**
     * key
     */
    private fun combineKey(rateLimiter: RateLimiter): String {
        val stringBuilder = StringBuilder(rateLimiter.key)
        if (rateLimiter.limitType == LimitType.IP) {
            stringBuilder.append(route.request().remoteAddress().host()).append("-")
        }
        val targetMethod = resourceInfo.resourceMethod
        val targetClass = resourceInfo.resourceClass
        stringBuilder.append(targetClass.name).append("-").append(targetMethod.name)
        return stringBuilder.toString()
    }


    /**
     * 获取请求方法注解的内容
     */
    private fun rateLimiter(): RateLimiter? {
        val method = resourceInfo.resourceMethod
        val resourceClass = resourceInfo.resourceClass

        val preAuthorize =
            method.getAnnotation(RateLimiter::class.java) ?: resourceClass.getAnnotation(RateLimiter::class.java)

        return preAuthorize
    }
}
