package com.cmdi.system.service

import com.cmdi.system.entity.SysUser
import com.cmdi.system.entity.SysUsers
import com.cmdi.system.repository.SysUserRepository
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
