package com.example.designpatterns.console

abstract class TemplateClass {
    protected abstract fun step1()
    protected abstract fun step2()
    protected abstract fun step3()

    fun doStuff() {
        step1()
        step2()
        step3()
    }
}

class ConcreteClass1 : TemplateClass() {
    override fun step1() {
        println("ConcreteClass1 step1")
    }

    override fun step2() {
        println("ConcreteClass1 step2")
    }

    override fun step3() {
        println("ConcreteClass1 step3")
    }
}

fun main() {
    val algorithm = ConcreteClass1()
    algorithm.doStuff()
}
