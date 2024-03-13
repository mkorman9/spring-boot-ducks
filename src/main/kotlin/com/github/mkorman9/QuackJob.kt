package com.github.mkorman9

import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class QuackJob : ApplicationRunner, Runnable {
    private val log = LoggerFactory.getLogger(QuackJob::class.java)
    private var isRunning = true

    override fun run(args: ApplicationArguments) {
        Thread.ofVirtual()
            .name("quack-job-thread")
            .start(this)
    }

    override fun run() {
        while (isRunning) {
            log.info("Quack!")
            Thread.sleep(2000)
        }
    }

    @PreDestroy
    fun destroy() {
        isRunning = false
        log.info("Stopping the quack")
    }
}
