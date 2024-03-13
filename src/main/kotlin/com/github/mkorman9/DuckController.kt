package com.github.mkorman9

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("/api/ducks")
class DuckController(
    private val duckService: DuckService
) {
    private val log = LoggerFactory.getLogger(DuckController::class.java)

    @GetMapping
    fun findAllDucks(): List<Duck> {
        return duckService.findAllDucks()
    }

    @GetMapping("/{id}")
    fun findDuck(@PathVariable("id") id: UUID): Duck {
        return duckService.findDuck(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Duck with given id was not found")
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    fun addDuck(
        @RequestBody @Valid payload: DuckAddPayload,
        token: JwtAuthenticationToken
    ): UUID {
        log.info("User ${token.name} is adding a duck")
        return duckService.addDuck(payload)
    }
}
