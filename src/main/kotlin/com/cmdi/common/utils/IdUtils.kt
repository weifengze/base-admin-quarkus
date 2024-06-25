package com.cmdi.common.utils

import java.util.UUID

class IdUtils {

    companion object {
        /**
         * 简单没有分隔线的UUID
         */
        fun randomSimpleUUID() = UUID.randomUUID().toString().replace("-","")

        fun randomUUID() = UUID.randomUUID().toString()
    }

}
