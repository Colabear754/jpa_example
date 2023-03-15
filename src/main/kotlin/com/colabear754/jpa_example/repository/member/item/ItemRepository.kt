package com.colabear754.jpa_example.repository.member.item

import com.colabear754.jpa_example.entity.item.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemRepository : JpaRepository<Item, UUID>