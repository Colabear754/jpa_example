package com.colabear754.jpa_example.exceptions

class NotEnoughStockException(
    override val message: String
) : RuntimeException()