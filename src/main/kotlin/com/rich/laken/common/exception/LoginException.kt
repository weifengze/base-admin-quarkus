package com.rich.laken.common.exception

class LoginException(
    /**
     * 错误消息
     */
    private val errorMessage: String,
) : RuntimeException() {

    override val message: String = errorMessage

}
