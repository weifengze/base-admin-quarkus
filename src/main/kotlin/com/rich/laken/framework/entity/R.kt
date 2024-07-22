package com.rich.laken.framework.entity

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * 响应体
 *
 * @author weifengze
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class R<T> {
    /**
     * 响应码
     */
    var code = 0

    /**
     * 操作信息
     */
    var msg: String = ""

    /**
     * 分页总条数
     */
    var total: Long? = null

    /**
     * 响应数据
     */
    var data: Any? = null
        private set

    fun setData(data: T) {
        this.data = data
    }

    companion object {

        /** 成功  */
        private const val SUCCESS = 200

        /** 失败  */
        private const val FAIL = 500
        fun <T> ok(): R<T> {
            return restResult(null, SUCCESS, "操作成功")
        }

        fun <T> ok(data: T): R<T> {
            return restResult(data, SUCCESS, "操作成功")
        }

        fun <T> ok(data: T, msg: String): R<T> {
            return restResult(data, SUCCESS, msg)
        }

        fun <T> fail(): R<T> {
            return restResult(null, FAIL, "操作失败")
        }

        fun <T> fail(msg: String): R<T> {
            return restResult(null, FAIL, msg)
        }

        fun <T> fail(data: T): R<T> {
            return restResult(data, FAIL, "操作失败")
        }

        fun <T> fail(data: T, msg: String): R<T> {
            return restResult(data, FAIL, msg)
        }

        fun <T> fail(code: Int, msg: String): R<T> {
            return restResult(null, code, msg)
        }

//        fun <T> table(data: Page<T>?): R<T> {
//            val apiResult = R<T>()
//            apiResult.code = SUCCESS
//            apiResult.data = data?.records
//            apiResult.msg = "操作成功"
//            return apiResult
//        }

        private fun <T> restResult(data: Any?, code: Int, msg: String): R<T> {
            val apiResult = R<T>()
            apiResult.code = code
            apiResult.data = data
            apiResult.msg = msg
            return apiResult
        }
    }
}
