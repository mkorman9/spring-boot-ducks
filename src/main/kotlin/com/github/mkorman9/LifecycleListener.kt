package com.github.mkorman9

import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class LifecycleListener : ApplicationRunner {
    private val log = LoggerFactory.getLogger(LifecycleListener::class.java)

    override fun run(args: ApplicationArguments) {
        log.info("Application has started")
    }

    @PreDestroy
    fun destroy() {
        log.info("Application is stopped")
    }
}
