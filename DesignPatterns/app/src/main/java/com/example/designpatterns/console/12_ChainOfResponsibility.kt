package com.example.designpatterns.console

//AKA The Pipeline architecture
//e.g. useful in audio processing


data class Operation(val op: String, val x: Int, val y: Int)

abstract class Chain() {
    var next: Chain? = null
    abstract fun calculate(operation: Operation)
}

class Adder : Chain() {
    override fun calculate(operation: Operation) {
        if(operation.op == "add") {
            println("${operation.x} + ${operation.y} = ${operation.x + operation.y}")
        } else {
            next?.calculate(operation)
        }
    }
}

class Multiplier : Chain() {
    override fun calculate(operation: Operation) {
        if(operation.op == "mul") {
            println("${operation.x} * ${operation.y} = ${operation.x * operation.y}")
        } else {
            next?.calculate(operation)
        }
    }
}

fun main() {

    val adder = Adder()
    val multiplier = Multiplier()
    adder.next = multiplier

    adder.calculate(Operation("add", 2, 3))
    adder.calculate(Operation("mul", 2, 3))
}
