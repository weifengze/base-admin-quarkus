package com.rich.laken.common.utils

import com.rich.laken.framework.filter.LoggingFilter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.jboss.logging.Logger
import org.ktorm.jackson.KtormModule
import java.text.SimpleDateFormat

object JsonUtils {
    private val LOGGER: Logger = Logger.getLogger(LoggingFilter::class.java.name)

    private val objectMapper = ObjectMapper()

    init {
        //对象的所有字段全部列入
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
    }

    /**
     * 对象转Json格式字符串
     *
     * @param obj 对象
     * @return Json格式字符串
     */
    fun <T> obj2String(obj: T?): String? {
        if (obj == null) {
            return null
        }
        try {
            return if (obj is String) obj else objectMapper.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            LOGGER.warn("Parse Object to String error : ${e.message}")
            return null
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     *
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    fun <T> obj2StringPretty(obj: T?): String? {
        if (obj == null) {
            return null
        }
        try {
            return if (obj is String) obj else objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            LOGGER.warn("Parse Object to String error : ${e.message}")
            return null
        }
    }

    /**
     * 解析Json对象
     *
     * @param json JSON数据
     * @param obj Class
     * @return 对象
     */
    fun <T> parseObject(json: String?, obj: Class<T>?): T? {
        var t: T? = null
        try {
            t = objectMapper.readValue(json, obj)
        } catch (e: JsonProcessingException) {
            LOGGER.error("JsonString转为自定义对象失败：${e.message}")
        }
        return t
    }
}
