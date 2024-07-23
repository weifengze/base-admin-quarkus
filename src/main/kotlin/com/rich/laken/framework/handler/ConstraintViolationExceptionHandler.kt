package com.rich.laken.framework.handler

import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.slf4j.LoggerFactory

/**
 * validation 异常处理
 */
@Provider
class ConstraintViolationExceptionHandler : ExceptionMapper<ConstraintViolationException> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun toResponse(exception: ConstraintViolationException?): Response {
        val message = exception?.constraintViolations?.first()?.message ?: ""
        logger.error("validation exception：{}", message)
        val errors = mapOf<String, Any>("code" to 400, "message" to message)
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(errors)
            .build()
    }
}
