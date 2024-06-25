package com.cmdi.system.entity

/**
 * 字典类型
 *
 * @author weifengze
 */
data class SysDictType(

    /**
     * 字典主键
     */
    var dictId: Long? = null,

    /**
     * 字典名称
     */
    var dictName: String? = null,

    /**
     * 字典类型
     */
    var dictType: String? = null,

    /**
     * 状态
     * 0正常 1停用
     */
    var status: Int? = null,
)
