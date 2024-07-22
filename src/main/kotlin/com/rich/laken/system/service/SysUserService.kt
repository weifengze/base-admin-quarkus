package com.rich.laken.system.service

import com.rich.laken.system.entity.SysUser
import com.rich.laken.system.entity.SysUsers
import com.rich.laken.system.repository.SysUserRepository
import jakarta.inject.Singleton
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.entity.filterColumns
import org.ktorm.entity.sequenceOf

@Singleton
class SysUserService(
    private val userRepository: SysUserRepository,
    private val database: Database,
) {


    fun selectUserList(user: SysUser?): List<SysUser> = userRepository.selectUserList(user)


}
