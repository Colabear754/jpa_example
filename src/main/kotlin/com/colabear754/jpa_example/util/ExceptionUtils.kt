package com.colabear754.jpa_example.util

fun fail(message: String): Nothing = throw NoSuchElementException(message)
fun typeMismatch(message: String): Nothing = throw IllegalArgumentException(message)