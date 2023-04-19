package com.colabear754.jpa_example.repositories.item

import com.colabear754.jpa_example.domain.entities.item.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemRepository : JpaRepository<Item, UUID>