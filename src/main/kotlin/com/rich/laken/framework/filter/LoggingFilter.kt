package com.rich.laken.framework.filter

import io.vertx.ext.web.RoutingContext
import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.ext.Provider
import org.jboss.logging.Logger
import java.io.*

/**
 * 请求日志输出
 */
@Provider
class LoggingFilter : ContainerRequestFilter, ContainerResponseFilter {
    companion object {
        private val LOGGER: Logger = Logger.getLogger(LoggingFilter::class.java.name)
    }

    @Inject
    private lateinit var route: RoutingContext

    /**
     * 过滤器函数，用于处理进入的容器请求。
     *
     * @param requestContext 容器请求上下文，包含请求的详细信息。
     */
    override fun filter(requestContext: ContainerRequestContext) {
        LOGGER.info("Remote IP: ${route.request().remoteAddress().host()}")
        LOGGER.info("RequestMethod: ${requestContext.request.method}")
        LOGGER.info("User-Agent: ${requestContext.headers["User-Agent"]}")
        if (requestContext.hasEntity()) {
            LOGGER.info("Request: ${requestContext.uriInfo.requestUri}")
            val inputStreamOriginal = requestContext.entityStream
            val requestBody = convertStreamToString(inputStreamOriginal)
            LOGGER.info("RequestBody: $requestBody")
            requestContext.entityStream = ByteArrayInputStream(requestBody.toByteArray())
        }
    }

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        // 记录响应信息
        LOGGER.info("Response: " + responseContext.status)
    }

    /**
     * 将InputStream转换为String。
     *
     * @param inputStream 需要被转换的输入流。
     * @return 返回输入流的字符串表示。
     * @throws IOException 如果在读取输入流时发生错误。
     */
    @Throws(IOException::class)
    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            sb.append(line)
        }
        return sb.toString()
    }
}


