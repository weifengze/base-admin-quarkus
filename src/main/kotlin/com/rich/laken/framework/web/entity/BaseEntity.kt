package com.rich.laken.framework.web.entity

import java.util.*

open class BaseEntity(

    var createBy: String? = null,

    var createTime: Date? = null,

    var updateBy: String? = null,

    var updateTime: Date? = null,

    var remark: String? = null,

    var pageNum: Long = 1,

    var pageSize: Long = 10,
)

