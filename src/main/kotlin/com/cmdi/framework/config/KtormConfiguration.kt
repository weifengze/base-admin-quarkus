package com.cmdi.framework.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Singleton
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

@Singleton
class KtormConfiguration {

    @ConfigProperty(name = "quarkus.datasource.username")
    lateinit var username: String

    @ConfigProperty(name = "quarkus.datasource.password")
    lateinit var password: String

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    lateinit var url: String

    @ConfigProperty(name = "quarkus.datasource.jdbc.driver")
    lateinit var driver: String

    @ApplicationScoped
    fun database(): Database {
        val hikariConfig = HikariConfig()
        hikariConfig.driverClassName = driver
        hikariConfig.jdbcUrl = url
        hikariConfig.username = username
        hikariConfig.password = password
        hikariConfig.maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2
        return Database.connect(HikariDataSource(hikariConfig), logger = ConsoleLogger(LogLevel.DEBUG))
    }
}
