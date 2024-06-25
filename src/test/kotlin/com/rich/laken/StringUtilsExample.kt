package com.rich.laken

import org.apache.commons.lang3.StringUtils

fun main() {
    println(StringUtils.isNotEmpty(""))
    println(StringUtils.isNotEmpty(" "))
    println(StringUtils.isNotEmpty(null))
    println(StringUtils.isNoneEmpty(""))
    println(StringUtils.isNoneEmpty(" "))
    println(StringUtils.isNoneEmpty(null))
    println(StringUtils.isNotBlank(""))
    println(StringUtils.isNotBlank(" "))
    println(StringUtils.isNotBlank(null))
    println(StringUtils.isNoneBlank(""))
    println(StringUtils.isNoneBlank(" "))
    println(StringUtils.isNoneBlank(null))

    listOf("y", "x,y,z")

}
