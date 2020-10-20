package com.example.designpatterns.console

interface Item {
    val description: String
    val cost: Double

    fun print() = println("$description costs £$cost")
}

class Pizza( override val description: String, override val cost: Double) : Item

class HamDecorator(private val item: Item): Item {
    override val description: String
        get() = item.description + " with Ham"
    override val cost: Double
        get() = item.cost + 1.50
}

class PineappleDecorator(private val item: Item): Item {
    override val description: String
        get() = item.description + " with Pineapple"
    override val cost: Double
        get() = item.cost + 2.00
}


fun main() {

    val hawaiianPizza = HamDecorator(PineappleDecorator(Pizza("Pizza", 10.00)))
    hawaiianPizza.print()

}
