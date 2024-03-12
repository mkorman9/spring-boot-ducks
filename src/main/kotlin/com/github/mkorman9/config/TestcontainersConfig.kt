package com.github.mkorman9.config

import org.springframework.boot.devtools.restart.RestartScope
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.testcontainers.containers.PostgreSQLContainer

@Configuration(proxyBeanMethods = false)
@Profile("default")
class TestcontainersConfig {
    @Bean
    @ServiceConnection
    @RestartScope
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:16")
    }
}
