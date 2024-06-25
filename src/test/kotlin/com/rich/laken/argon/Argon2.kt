package com.rich.laken.argon

import com.cmdi.framework.security.UsernamePasswordAuthentication

fun main() {
//    val password = "admin123" + "admin"
//    val argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64)
//    // 23
////    val findIterations = Argon2Helper.findIterations(argon2, 1000, 65536, 1)
//    val hashPassword = argon2.hash(23, 65536, 1, password.toByteArray())
//    println(argon2.hash(23, 65536, 1, password.toByteArray()))
//    println(argon2.hash(23, 65536, 1, password.toByteArray()))
//    println(argon2.hash(23, 65536, 1, password.toByteArray()))
//    println(argon2.hash(23, 65536, 1, password.toByteArray()))
//    println(hashPassword)
//    val verify = argon2.verify(hashPassword, password.toByteArray())
//    println(verify)


    val generateHashPassword = UsernamePasswordAuthentication.generateHashPassword("admin@admin.com", "admin")
    println(generateHashPassword)
    println(UsernamePasswordAuthentication.verifyPassword(generateHashPassword, "admin@admin.comadmin"))
}
