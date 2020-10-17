package com.example.coroutinetut

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}


suspend fun task(name: String, delayMs: Long) {
    println("$name STARTED..")
    delay(delayMs)
    println("$name FINISHED")
}


fun main() = runBlocking {

    val job = launch {
        @Suppress("DeferredResultUnused")
        withTimeout(5000) {
            async { task("task1", 3000) }
            async { task("task2", 2000) }
            async { task("task3", 1000) }
        }
    }

    job.join()
    println(job)
}