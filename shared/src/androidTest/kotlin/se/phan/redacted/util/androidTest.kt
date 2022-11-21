package se.phan.redacted.util

import org.junit.Assert.assertTrue
import org.junit.Test
import se.phan.redacted.util.Greeting

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}