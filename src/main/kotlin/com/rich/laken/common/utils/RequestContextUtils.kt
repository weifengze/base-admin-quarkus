package com.rich.laken.common.utils

import org.jboss.resteasy.reactive.server.core.CurrentRequestManager

object RequestContextUtils {

    private val requestContext = CurrentRequestManager.get()

    fun getParameter(name: String): String? = requestContext.uriInfo.queryParameters.getFirst(name)

    fun getParameterToInt(name: String): Int? = getParameter(name)?.toIntOrNull()

}
