package com.rich.laken.system.entity

/**
 * 字典数据
 *
 * @author weifengze
 */
data class SysDictData(
    /**
     * 字典编码
     */
    var dictCode: Long? = null,

    /**
     * 字典排序
     */
    var dictSort: Long? = null,

    /**
     * 字典标签
     */
    var dictLabel: String? = null,

    /**
     * 字典键值
     */
    var dictValue: String? = null,

    /**
     * 字典类型
     */
    var dictType: String? = null,

    /**
     * 样式属性（其他样式扩展）
     */
    var cssClass: String? = null,

    /**
     * 表格回显样式
     */
    var listClass: String? = null,

    /**
     * 是否默认
     * Y是 N否
     */
    var isDefault: String? = null,

    /**
     * 状态
     * 0正常 1停用
     */
    var status: Int? = null,
)
