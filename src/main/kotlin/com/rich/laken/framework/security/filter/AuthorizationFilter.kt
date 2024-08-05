package com.rich.laken.framework.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.rich.laken.common.exception.UnauthorizedException
import com.rich.laken.common.utils.BrowserDetector
import com.rich.laken.framework.config.AuthorizationConfig
import com.rich.laken.framework.security.service.TokenService
import io.vertx.ext.web.RoutingContext
import jakarta.annotation.PostConstruct
import jakarta.annotation.Priority
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Priorities
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.logging.Logger

/**
 * 权限过滤
 */
@ApplicationScoped
@Priority(Priorities.USER + 200)
@Provider
class AuthorizationFilter(
    private val route: RoutingContext,
    private val tokenService: TokenService,
    private val authorizationConfig: AuthorizationConfig,
) : ContainerRequestFilter {
    private val logger = Logger.getLogger(AuthorizationFilter::class.java.name)

    @ConfigProperty(name = "authorization.permit-patterns")
    private lateinit var permitPatterns: List<String>

    private var regex = listOf<Regex>()

    @PostConstruct
    fun init() {
        regex = authorizationConfig.permitPatterns().map { it.toRegexPattern() }
    }

    override fun filter(requestContext: ContainerRequestContext) {
        val uri = route.request().uri()
        val jwtToken = requestContext.headers["Authorization"]?.firstOrNull()
        if (!uri.matchesAnyPattern(regex)) {
            if (jwtToken.isNullOrBlank()) {
                throw UnauthorizedException()
            } else {
                isValidToken(jwtToken, requestContext)
            }
        }
    }

    /**
     * 验证token
     *
     * @param jwtToken LoginUser
     * @param requestContext ContainerRequestContext
     */
    private fun isValidToken(jwtToken: String, requestContext: ContainerRequestContext) {
        val loginUser = tokenService.getLoginUser(jwtToken)
        val host = route.request().remoteAddress().host()
        val detectBrowser = BrowserDetector.detectBrowser(requestContext.getHeaderString("User-Agent")).name
        if (loginUser == null) {
            logger.error("LoginUser is null")
            throw UnauthorizedException()
        }

        if (loginUser.browser != detectBrowser || loginUser.ipaddr != host) {
            logger.error("Unauthorized access attempt: expected browser ${loginUser.browser}, detected browser $detectBrowser; expected IP ${loginUser.ipaddr}, detected IP $host")
            throw UnauthorizedException()
        }

        tokenService.verifyToken(loginUser)
    }

    /**
     * 构建一个响应对象。
     *
     * @param message 响应中的消息内容。
     * @param details 响应中的详细信息。
     * @return 返回一个设置了状态码、内容类型、实体的响应对象。
     */
    private fun buildResponse(message: String, details: String, uri: String): Response {
        // 设置响应状态为内部服务器错误，设置内容类型为JSON，构建响应实体
        return Response.status(Response.Status.UNAUTHORIZED)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            // 将包含消息、时间戳和详细信息的Map转换为JSON字符串作为实体
            .entity(
                ObjectMapper().writeValueAsString(
                    mapOf(
                        "message" to message,
                        "details" to details,
                        "uri" to uri,
                        "timestamp" to System.currentTimeMillis()
                    )
                )
            )
            .build()
    }

    // 扩展函数：将通配符模式转换为正则表达式模式
    fun String.toRegexPattern(): Regex {
        val regexPattern = this.replace("/**", "(/.*)?")
        return Regex("^$regexPattern$")
    }

    // 扩展函数：检查URL是否匹配任意一个模式
    fun String.matchesAnyPattern(patterns: List<Regex>): Boolean {
        return patterns.any { it.matches(this) }
    }
}
