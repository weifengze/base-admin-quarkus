package com.rich.laken

fun main() {
    val permitPatterns = "/login,/sign,/commons/**"
    val url1 = "/login111"
    val url2 = "/commons/download"

    // 将通配符模式转换为正则表达式
    val patterns = permitPatterns.split(",").map { it.toRegexPattern() }

    println(url1.matchesAnyPattern(patterns)) // 输出: true
    println(url2.matchesAnyPattern(patterns)) // 输出: true
}

// 扩展函数：将通配符模式转换为正则表达式模式
fun String.toRegexPattern(): Regex {
    val regexPattern = this.replace("**", ".*").replace("*", "[^/]*")
    return Regex("^$regexPattern$")
}

// 扩展函数：检查URL是否匹配任意一个模式
fun String.matchesAnyPattern(patterns: List<Regex>): Boolean {
    return patterns.any { it.matches(this) }
}
