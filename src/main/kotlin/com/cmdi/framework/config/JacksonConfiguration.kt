package com.cmdi.framework.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Singleton
import org.ktorm.jackson.KtormModule
import java.text.SimpleDateFormat


@Singleton
class JacksonConfiguration {

    @ApplicationScoped
    fun objectMapperConfig(): ObjectMapper {
        val objectMapper = ObjectMapper() //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.registerModule(KtormModule())
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }
}
