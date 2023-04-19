package com.colabear754.jpa_example.domain.entities

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    val createdBy: String? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    var lastModifiedBy: String? = null,
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
)