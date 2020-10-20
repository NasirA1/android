package com.example.designpatterns.console

// The Strategy Pattern can be useful for adding new behaviour, e.g. Flying, to a class hierarchy
// in a nice & clean and flexibly way.

abstract class Animal(private val flyable: Flyable, val name: String) {
    abstract fun makeSound()

    fun fly() = println("${javaClass.simpleName} $name " + flyable.fly())
}

class Dog(name: String) : Animal(NoFlyer(), name) {
    override fun makeSound() {
        println("$name: woof woof!")
    }
}

class Duck(name: String) : Animal(HighFlyer(), name) {
    override fun makeSound() {
        println("$name: quack quack!")
    }
}

interface Flyable {
    fun fly(): String
}

class HighFlyer: Flyable {
    override fun fly() = "Flying high!"
}

class NoFlyer: Flyable {
    override fun fly() = "Cannot fly"
}

fun main() {
    Dog("fido").fly()
    Duck("tweety").fly()
}