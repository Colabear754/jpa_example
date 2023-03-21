package com.colabear754.jpa_example.util

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionHelper {
    @Transactional
    fun execute(block: () -> Unit) {
        block()
    }
}