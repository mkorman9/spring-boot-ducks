package com.github.mkorman9

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class FeedingJob {
    private val log = LoggerFactory.getLogger(FeedingJob::class.java)

    @Scheduled(fixedRate = 10_000)
    fun onFeeding() {
        log.info("Feeding time!")
    }
}
