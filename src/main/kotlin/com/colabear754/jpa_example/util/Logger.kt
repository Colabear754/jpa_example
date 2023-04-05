package com.colabear754.jpa_example.util

import org.slf4j.LoggerFactory

abstract class Logger {
    val log = LoggerFactory.getLogger(this.javaClass)!!
}