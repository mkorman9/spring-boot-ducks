package com.github.mkorman9

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.dsl.where
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class DuckService(
    private val db: Database
) {
    fun findAllDucks(): List<Duck> {
        return db
            .from(DuckTable)
            .select()
            .map { row ->
                Duck(
                    id = row[DuckTable.id]!!,
                    name = row[DuckTable.name]!!,
                    height = row[DuckTable.height]!!,
                    createdAt = row[DuckTable.createdAt]!!
                )
            }
    }

    fun findDuck(id: UUID): Duck? {
        return db
            .from(DuckTable)
            .select()
            .where { DuckTable.id eq id }
            .map { row ->
                Duck(
                    id = row[DuckTable.id]!!,
                    name = row[DuckTable.name]!!,
                    height = row[DuckTable.height]!!,
                    createdAt = row[DuckTable.createdAt]!!
                )
            }
            .firstOrNull()
    }

    fun addDuck(payload: DuckAddPayload): UUID {
        val id = UUID.randomUUID()

        db.insert(DuckTable) {
            set(it.id, id)
            set(it.name, payload.name)
            set(it.height, payload.height)
            set(it.createdAt, Instant.now())
        }

        return id
    }
}

data class Duck(
    val id: UUID,
    val name: String,
    val height: Int,
    val createdAt: Instant
)

data class DuckAddPayload(
    @field:NotBlank @field:Size(max = 255) val name: String,
    @field:Min(value = 1) val height: Int
)
