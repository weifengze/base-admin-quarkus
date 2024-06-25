package com.cmdi.common.exception

class LoginException(
    /**
     * 错误消息
     */
    private val errorMessage: String,
) : RuntimeException() {

    override val message: String = errorMessage

}
