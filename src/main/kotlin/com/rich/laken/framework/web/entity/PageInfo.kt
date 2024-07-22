package com.rich.laken.framework.web.entity

data class PageInfo<T : Any>(
    val data: List<T>,
    val total: Int,
)

