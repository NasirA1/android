package com.example.designpatterns.console

import java.io.Closeable

// The Observer Pattern useful for notifying clients of a change
// Caution: must unsubscribe to avoid resource leakage

interface Subject<T> {
    fun substribe(o: Observer<T>)
    fun unsubscribe(o: Observer<T>)
    fun notify(msg: T)
}

interface Observer<T> {
    fun notify(msg: T)
}

class OS : Subject<String> {
    private val observers = mutableSetOf<Observer<String>>()

    override fun substribe(o: Observer<String>) {
        observers.add(o)
    }

    override fun unsubscribe(o: Observer<String>) {
        observers.remove(o)
    }

    override fun notify(msg: String) {
        observers.forEach { it.notify(msg) }
    }
}

class App1(private val os: OS): Observer<String>, Closeable {

    init { os.substribe(this) }

    override fun notify(msg: String) {
        println("App1 - Alert received from OS: $msg")
    }

    override fun close() {
        os.unsubscribe(this)
    }
}

class App2(private val os: OS): Observer<String>, Closeable {

    init { os.substribe(this) }

    override fun notify(msg: String) {
        println("App2 - Alert received from OS: $msg")
    }

    override fun close() {
        os.unsubscribe(this)
    }
}

fun main() {
    val os = OS()

    val app1 = App1(os)
    val app2 = App2(os)

    os.notify("Memory low")

    app1.close()
    app2.close()
}