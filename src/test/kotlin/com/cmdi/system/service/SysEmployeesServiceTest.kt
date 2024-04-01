package com.cmdi.system.service

import com.cmdi.common.utils.HttpUtils
import com.cmdi.system.mapper.SysEmployeesMapper
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class SysEmployeesServiceTest {


    @Inject
    private lateinit var employeesService: SysEmployeesService

    @Inject
    private lateinit var employeesMapper: SysEmployeesMapper

    @Test
    fun testSelectAllCount() {
        val sendPostRequest = HttpUtils.sendPostRequest("https://tenapi.cn/v2/bilihot", "{}")
        val readValue =
            ObjectMapper().readValue(sendPostRequest.get().body(), object : TypeReference<Map<String, Any>>() {})
        println(readValue["data"])
    }
}
