package com.rich.laken.common.utils

import java.util.regex.Pattern

data class BrowserInfo(val name: String, val version: String?)

/**
 * User-Agent 检测
 */
class BrowserDetector {
    companion object {

        private val userAgentPatters = mapOf(
            "Microsoft Edge" to Pattern.compile("Edg/(\\d+\\.\\d+?)"),
            "Firefox" to Pattern.compile("Firefox/(\\d+\\.\\d+?)"),
            "Safari" to Pattern.compile("Version/(\\d+\\.\\d+?).*Safari"),
            "QQBrowser" to Pattern.compile("QQBrowser/(\\d+\\.\\d+?)"),
            "Chrome" to Pattern.compile("Chrome/(\\d+\\.\\d+?)"),
        )

        fun detectBrowser(userAgent: String): BrowserInfo {
            return userAgentPatters.entries.fold(BrowserInfo("Unknown Browser", null)) { acc, (browserName, pattern) ->
                val matcher = pattern.matcher(userAgent)
                if (matcher.find()) {
                    val version = matcher.group(1)
                    return BrowserInfo(browserName, version)
                }
                acc
            }
        }
    }
}
