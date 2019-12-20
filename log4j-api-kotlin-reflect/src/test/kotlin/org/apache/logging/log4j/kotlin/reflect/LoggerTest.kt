package org.apache.logging.log4j.kotlin.reflect

import org.apache.logging.log4j.junit.LoggerContextRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class LoggerTest {
    companion object {
        val result = "foo"
    }

    @Rule
    @JvmField var init = LoggerContextRule("InfoLogger.xml")

    @Test
    fun `Lambda functions are evaluated if the level is high enough`() {
        var count = 0
        fun lamdaFun(): String {
            count++
            return result
        }
        val log = logger()
        log.info { lamdaFun() }
        assertTrue { count == 1 }
    }

    @Test
    fun `Lambda functions are not evaluated if the level is low enough`() {
        var count = 0
        fun lamdaFun(): String {
            count++
            return result
        }
        val log = logger()
        log.debug { lamdaFun() }
        assertTrue { count == 0 }
    }

}