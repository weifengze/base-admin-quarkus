package com.rich.laken.system.repository

import com.rich.laken.system.entity.SysDepts
import com.rich.laken.system.entity.SysUser
import com.rich.laken.system.entity.SysUsers
import com.rich.laken.system.entity.sysUsers
import jakarta.inject.Singleton
import org.apache.commons.lang3.StringUtils
import org.ktorm.dsl.*
import org.ktorm.entity.drop
import org.ktorm.entity.take
import org.ktorm.entity.toList

@Singleton
class SysUserRepository : BaseRepository<SysUser, SysUsers>(SysUsers) {

    fun selectUserList(user: SysUser?): List<SysUser> {
        val a = database.from(SysUsers)
            .leftJoin(SysDepts, on = SysUsers.deptId eq SysDepts.deptId)
            .select()
            .whereWithConditions {
                it += SysUsers.delFlag eq 0
                if (user != null) {
                    if (user.userId != null && user.userId != 0L) {
                        it += SysUsers.userId eq user.userId!!
                    }
                    if (StringUtils.isNoneBlank(user.userName)) {
                        it += SysUsers.userName like "%${user.userName}%"
                    }
                    if (user.status != null) {
                        it += SysUsers.status eq user.status!!
                    }
                    if (StringUtils.isNotBlank(user.phonenumber)) {
                        it += SysUsers.phonenumber like "%${user.phonenumber}%"
                    }
                    if (user.deptId != null && user.deptId != 0L) {
                        it += (SysUsers.deptId eq user.deptId!!) or (findInSet(SysUsers.deptId, SysDepts.ancestors))
                    }
                }
            }.database.sysUsers.drop(0).take(20)
        return a.toList()
    }
}
