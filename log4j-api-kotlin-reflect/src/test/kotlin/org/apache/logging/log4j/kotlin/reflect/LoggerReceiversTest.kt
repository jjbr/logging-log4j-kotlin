/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.kotlin.reflect

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.junit.LoggerContextRule
import org.apache.logging.log4j.kotlin.KotlinLogger
import org.apache.logging.log4j.kotlin.reflect.support.withListAppender
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LoggerReceiversTest {
  @Rule @JvmField var init = LoggerContextRule("InfoLogger.xml")

  @Test
  fun `Logging from a function instantiation via class receiver logs the correct class name`() {
    runTest(LoggerTest.logger())
  }

  @Test
  fun `Logging from a function instantiation via object receiver logs the correct class name`() {
    runTest(LoggerTest().logger())
  }

  private fun runTest(log: KotlinLogger) {
    val msg = "This is an error log."
    val msgs = withListAppender { _, _ ->
      log.error(msg)
    }

    assertEquals(1, msgs.size.toLong())

    msgs.first().also {
      assertEquals(Level.ERROR, it.level)
      assertEquals(msg, it.message.format)
      assertEquals(LoggerTest::class.qualifiedName, it.loggerName)
    }
  }
}
