package com.example.kotlinlangfeatures

import kotlinx.coroutines.*


suspend fun printNumbers() {
    (1..10).forEach {
        println(it)
        delay(500)
    }
}


fun main() {

    runBlocking {
        launch {
            //SEQ
            printNumbers()
            printNumbers()
            launch {
                //PAR
                launch { printNumbers() }
                launch { printNumbers() }
            }
        }
    }

}