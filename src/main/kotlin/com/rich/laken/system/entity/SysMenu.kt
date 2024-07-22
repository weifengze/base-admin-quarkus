package com.rich.laken.system.entity

/**
 * 菜单权限
 *
 * @author weifengze
 */
data class SysMenu(
    /**
     * 菜单ID
     */
    var menuId: Long? = null,

    /**
     * 菜单名称
     */
    var menuName: String? = null,

    /**
     * 父菜单ID
     */
    var parentId: Long? = null,

    /**
     * 显示顺序
     */
    var orderNum: Int? = null,

    /**
     * 路由地址
     */
    var path: String? = null,

    /**
     * 组件路径
     */
    var component: String? = null,

    /**
     * 组件名称
     */
    var componentName: String? = null,

    /**
     * 路由参数
     */
    var query: String? = null,

    /**
     * 是否为外链
     * 0是 1否
     */
    var isFrame: Int? = null,

    /**
     * 是否缓存
     * 0缓存 1不缓存
     */
    var isCache: Int? = null,

    /**
     * 类型
     * M目录 C菜单 F按钮
     */
    var menuType: String? = null,

    /**
     * 显示状态
     * （0显示 1隐藏）
     */
    var visible: Int? = null,

    /**
     * 菜单状态
     * 0正常 1停用
     */
    var status: Int? = null,

    /**
     * 权限字符串
     */
    var perms: String? = null,

    /**
     * 菜单图标
     */
    var icon: String? = null,

    /**
     * 子菜单
     */
//    @TableField(exist = false)
    val children: List<SysMenu>? = emptyList(),
)
