package com.rich.laken.common.enums

/**
 * 用户状态
 *
 * @author administrator
 */
enum class UserStatus(val code: Int, val info: String) {
    OK(0, "正常"),
    DISABLE(1, "停用"),
    DELETED(2, "删除");
}
