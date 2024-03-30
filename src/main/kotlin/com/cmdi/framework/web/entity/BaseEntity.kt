package com.cmdi.framework.web.entity

import com.baomidou.mybatisplus.annotation.TableField
import java.util.*

open class BaseEntity(
    @TableField(exist = false)
    var createBy: String? = null,
    @TableField(exist = false)
    var createTime: Date? = null,
    @TableField(exist = false)
    var updateBy: String? = null,
    @TableField(exist = false)
    var updateTime: Date? = null,
    @TableField(exist = false)
    var remark: String? = null,
    @TableField(exist = false)
    var pageNum: Long = 1,
    @TableField(exist = false)
    var pageSize: Long = 10,
)

