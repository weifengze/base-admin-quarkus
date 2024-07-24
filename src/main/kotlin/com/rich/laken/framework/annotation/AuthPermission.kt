package com.rich.laken.framework.annotation

import jakarta.interceptor.InterceptorBinding

/**
 * 操作权限校验
 */
@InterceptorBinding
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.CLASS)
annotation class AuthPermission(val value: String)
