package com.rich.laken.framework.security

import jakarta.ws.rs.core.SecurityContext
import java.security.Principal

class AuthenticationSecurityContext(
    private val principal: Principal,
    private val isSecure: Boolean,
    private val authenticationScheme: String,
) : SecurityContext {
    override fun getUserPrincipal(): Principal {
        return principal
    }

    override fun isUserInRole(role: String): Boolean {
        // Implement role checking logic
        return false
    }

    override fun isSecure(): Boolean {
        return isSecure
    }

    override fun getAuthenticationScheme(): String {
        return authenticationScheme
    }
}
