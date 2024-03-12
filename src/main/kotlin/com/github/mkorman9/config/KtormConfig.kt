package com.github.mkorman9.config

import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class KtormConfig {
    @Bean
    fun database(dataSource: DataSource) = Database.connect(
        dataSource,
        dialect = PostgreSqlDialect()
    )
}
