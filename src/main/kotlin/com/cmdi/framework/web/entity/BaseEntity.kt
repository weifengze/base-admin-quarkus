package com.cmdi.framework.web.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
open class BaseEntity(

    var createBy: String? = null,

    @Contextual var createTime: Date? = null,

    var updateBy: String? = null,

    @Contextual var updateTime: Date? = null,

    var remark: String? = null,

    var pageNum: Int = 1,

    var pageSize: Int = 10,
)

