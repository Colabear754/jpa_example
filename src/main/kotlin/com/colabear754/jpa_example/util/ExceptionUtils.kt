package com.colabear754.jpa_example.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(message: String): Nothing {
    throw NoSuchElementException(message)
}

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T = findByIdOrNull(id) ?: fail("${id}에 해당하는 회원 정보를 찾을 수 없습니다.")