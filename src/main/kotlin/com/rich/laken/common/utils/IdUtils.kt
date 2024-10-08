package com.rich.laken.common.utils

import java.util.*

/**
 * id 工具类
 */
class IdUtils {

    companion object {
        /**
         * 简单没有分隔线的UUID
         */
        fun randomSimpleUUID() = UUID.randomUUID().toString().replace("-", "")

        fun randomUUID() = UUID.randomUUID().toString()
    }

}
