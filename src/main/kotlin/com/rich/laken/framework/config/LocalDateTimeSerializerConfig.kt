package com.rich.laken.framework.config

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import jakarta.enterprise.context.ApplicationScoped
import java.time.format.DateTimeFormatter

/**
 * LocalDateTime 序列化配置
 */
@ApplicationScoped
class LocalDateTimeSerializerConfig {

    @ApplicationScoped
    fun localDateTimeDeserializer(): LocalDateTimeSerializer? =
        LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

}
