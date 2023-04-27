package com.colabear754.jpa_example.util

fun fail(message: String): Nothing = throw NoSuchElementException(message)
val badRequest = { message: String -> IllegalArgumentException(message) }