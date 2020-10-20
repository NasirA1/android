package com.example.designpatterns.console

interface Expr {
    fun evaluate(): Int
}

class Num(val value: Int) : Expr {
    override fun evaluate(): Int {
        return value
    }
}

class Add(val left: Expr, val right: Expr) : Expr {
    override fun evaluate(): Int {
        return left.evaluate() + right.evaluate()
    }
}

class Mul(val left: Expr, val right: Expr) : Expr {
    override fun evaluate(): Int {
        return left.evaluate() * right.evaluate()
    }
}


fun main() {

    val r = Add(Mul(Num(2), Num(5)), Num(4)).evaluate()
    println("2 * 5 + 4 = $r")

}