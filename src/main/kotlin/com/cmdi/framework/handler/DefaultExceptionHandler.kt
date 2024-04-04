package com.cmdi.framework.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

/**
 * 默认异常处理器类
 */
@Provider
class DefaultExceptionHandler : ExceptionMapper<Exception> {

    private val objectMapper = ObjectMapper()

    override fun toResponse(exception: Exception): Response {
        val errorDetails = exception.localizedMessage ?: exception.message ?: "未提供详细信息"
        val errorMessage = "服务器异常！详情：$errorDetails"
        val jsonPayload =
            objectMapper.writeValueAsString(
                mapOf(
                    "message" to errorMessage,
                    "timestamp" to System.currentTimeMillis(),
                    "details" to errorDetails
                )
            )
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .entity(jsonPayload)
            .build()
    }
}
