package org.apache.logging.log4j.kotlin.reflect

import org.apache.logging.log4j.kotlin.KotlinLogger
import org.apache.logging.log4j.spi.ExtendedLogger
import kotlin.reflect.full.companionObject
import org.apache.logging.log4j.LogManager

/**
 * Logger instantiation by function. Use: `val log = logger()`. The logger will be named according to the
 * receiver of the function, which can be a class or object. An alternative for explicitly named loggers is
 * the [logger(String)] function.
 */
@Suppress("unused")
inline fun <reified T : Any> T.logger() = loggerOf(T::class.java)

fun loggerDelegateOf(ofClass: Class<*>): ExtendedLogger {
    return LogManager.getContext(ofClass.classLoader, false).getLogger(unwrapCompanionClass(ofClass).name)
}

fun loggerOf(ofClass: Class<*>): KotlinLogger {
    return KotlinLogger(loggerDelegateOf(ofClass))
}

// unwrap companion class to enclosing class given a Java Class
private fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return if (ofClass.enclosingClass?.kotlin?.companionObject?.java == ofClass) {
        ofClass.enclosingClass
    } else {
        ofClass
    }
}
