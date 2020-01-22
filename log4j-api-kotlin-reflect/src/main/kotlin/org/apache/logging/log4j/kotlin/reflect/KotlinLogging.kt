package org.apache.logging.log4j.kotlin.reflect

import org.apache.logging.log4j.kotlin.KotlinLogger
import org.apache.logging.log4j.kotlin.KotlinLogging
import kotlin.reflect.KClass

fun KotlinLogging.logger(clazz: Class<*>): KotlinLogger = loggerOf(clazz)
fun KotlinLogging.logger(clazz: KClass<*>): KotlinLogger = loggerOf(clazz.java)
