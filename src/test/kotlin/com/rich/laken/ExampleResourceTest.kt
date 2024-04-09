package com.rich.laken

import io.quarkus.redis.datasource.RedisDataSource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.inject.Inject
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ExampleResourceTest {

    @Inject
    private lateinit var redisDataSource: RedisDataSource

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/hello")
            .then()
            .statusCode(200)
            .body(`is`("Hello from Quarkus REST"))
    }

    @Test
    fun testRedis() {
//        val hashCommands = redisDataSource.hash(Any::class.java)
//        hashCommands.hset("test", mapOf("name" to "weifengze", "age" to "18"))
        val value = redisDataSource.value(Any::class.java)
//        value.set("test:json",
//            ObjectMapper().writeValueAsString(mapOf("name" to "weifengze", "age" to "18"))
//        )
    }

}
