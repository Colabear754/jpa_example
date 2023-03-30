package com.colabear754.jpa_example.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(message: String): Nothing {
    throw NoSuchElementException(message)
}

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, message: String = "해당 ID의 데이터가 존재하지 않습니다."): T = findByIdOrNull(id) ?: fail(message)