package com.rich.laken.common.exception

/**
 * 登录异常
 */
class LoginException(
    /**
     * 错误消息
     */
    private val errorMessage: String,
) : RuntimeException() {

    override val message: String = errorMessage

}
