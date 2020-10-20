package com.example.designpatterns.console

import kotlin.concurrent.thread


class Singleton private constructor() {

    init {
        //construction logic
    }

    companion object {
        @Volatile private lateinit var instance_: Singleton

        val instance: Singleton get() {
            synchronized(this) {
            if(!this::instance_.isInitialized) {
                println("Singleton accessed for the first time. Constructing it..")
                    instance_ = Singleton()
                }
            }
            return instance_
        }
    }

    fun foo() {
        println("Hi from Singleton")
    }
}


object KotlinSingleton {
    fun foo(){}
    fun bar(){}
}


fun main() {

    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }
    thread { Singleton.instance.foo() }

    KotlinSingleton.bar()
    KotlinSingleton.foo()

}