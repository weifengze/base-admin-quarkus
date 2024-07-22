package com.cmdi.system.repository

import com.rich.laken.common.utils.JsonUtils
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.rich.laken.system.repository.SysUserRepository
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.jackson.KtormModule
import java.text.SimpleDateFormat


@QuarkusTest
class SysUserRepositoryTest {

    @Inject
    private lateinit var userRepository: SysUserRepository

    @Inject
    private lateinit var database: Database

    companion object {
        fun <T> toJSONString(obj: T?): String? {
            val timeModule = JavaTimeModule()


//            // LocalDateTime
//            timeModule.addSerializer(
//                LocalDateTime::class.java,
//                LocalDateTimeSerializer(
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"))
//                )
//            )
//            timeModule.addDeserializer(
//                LocalDateTime::class.java,
//                LocalDateTimeDeserializer(
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"))
//                )
//            )

            val objectMapper = ObjectMapper()
            objectMapper.registerModule(KtormModule())
            objectMapper.registerModule(timeModule)
            objectMapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT)
            //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
            objectMapper.setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            return if (obj is String) obj else objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(obj)
        }
    }


    @Test
    fun findUserByUserName() {
        val message = userRepository.findOne { it.userName eq "admin" and (it.delFlag eq 0) }
        println(JsonUtils.obj2StringPretty(message))
    }
}
