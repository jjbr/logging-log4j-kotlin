package org.apache.logging.log4j.kotlin

import org.apache.logging.log4j.kotlin.logger as loggerOf

object KotlinLogging {

    fun logger(func: () -> Unit): KotlinLogger = loggerOf(func)

    fun logger(name: String): KotlinLogger = loggerOf(name)

}