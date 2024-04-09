package com.cmdi.framework.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.RoutingContext
import jakarta.annotation.Priority
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.Priorities
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ResourceInfo
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.logging.Logger

@ApplicationScoped
@Priority(Priorities.USER + 200)
@Provider
class AuthorizationFilter : ContainerRequestFilter {

    companion object {
        private val LOGGER: Logger = Logger.getLogger(AuthorizationFilter::class.java.name)
    }

    @ConfigProperty(name = "authorization.permit-patterns")
    private lateinit var permitPatterns: String

    @Context
    private lateinit var request: HttpServerRequest

    @Inject
    private lateinit var route: RoutingContext

//    @Inject
//    var jwt: JsonWebToken? = null

    @Context
    private lateinit var resourceInfo: ResourceInfo

    /**
     * Filter method called before a request has been dispatched to a resource.
     *
     *
     *
     * Filters in the filter chain are ordered according to their `jakarta.annotation.Priority` class-level annotation
     * value. If a request filter produces a response by calling [ContainerRequestContext.abortWith] method, the
     * execution of the (either pre-match or post-match) request filter chain is stopped and the response is passed to the
     * corresponding response filter chain (either pre-match or post-match). For example, a pre-match caching filter may
     * produce a response in this way, which would effectively skip any post-match request filters as well as post-match
     * response filters. Note however that a responses produced in this manner would still be processed by the pre-match
     * response filter chain.
     *
     *
     * @param requestContext request context.
     * @throws IOException if an I/O exception occurs.
     * @see PreMatching
     */
    override fun filter(requestContext: ContainerRequestContext) {
        val uri = route.request().uri()
        LOGGER.info("uri: $uri")
        val token = requestContext.headers["Authorized"]?.first()
        if (uri != "/login") {
            if (token == null) {
                LOGGER.error("token is no information")
                requestContext.abortWith(buildResponse("unauthorized", "Unauthorized Requests"))
            }
        }
        LOGGER.info("Remote IP: ${route.request().remoteAddress().host()}")
        LOGGER.info("User-Agent: ${requestContext.headers["User-Agent"]}")
    }

    /**
     * 构建一个响应对象。
     *
     * @param message 响应中的消息内容。
     * @param details 响应中的详细信息。
     * @return 返回一个设置了状态码、内容类型、实体的响应对象。
     */
    private fun buildResponse(message: String, details: String): Response {
        // 设置响应状态为内部服务器错误，设置内容类型为JSON，构建响应实体
        return Response.status(Response.Status.UNAUTHORIZED)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            // 将包含消息、时间戳和详细信息的Map转换为JSON字符串作为实体
            .entity(
                ObjectMapper().writeValueAsString(
                    mapOf(
                        "message" to message,
                        "timestamp" to System.currentTimeMillis(),
                        "details" to details
                    )
                )
            )
            .build()
    }
}
