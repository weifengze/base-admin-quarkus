package com.rich.laken.framework.handler

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

/**
 * 默认异常处理器类
 */
@Provider
class DefaultExceptionHandler : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception): Response {
        val errorDetails = exception.localizedMessage ?: exception.message ?: "未提供详细信息"

        when (exception) {
            is NotFoundException -> Response.status(Response.Status.NOT_FOUND)
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(mapOf("code" to 500, "message" to errorDetails, "timestamp" to System.currentTimeMillis()))
            .build()
    }
}
