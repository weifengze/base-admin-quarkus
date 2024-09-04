package com.rich.laken.framework.annotation

import com.rich.laken.common.enums.LimitType
import jakarta.interceptor.InterceptorBinding

/**
 * 限流注解
 */
@InterceptorBinding
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.CLASS)
annotation class RateLimiter(
    val key: String = "rate_limit:",
    val time: Int = 10,
    val count: Int = 100,
    val limitType: LimitType = LimitType.DEFAULT,
)

