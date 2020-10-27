package com.example.kotlinlangfeatures



abstract class Animal(private val flyer: Flyer) {
    abstract fun makeSound()
    fun fly() = flyer.fly()
}

class Dog: Animal(NonFlyer()) {
    override fun makeSound() {
        println("woof woof!")
    }
}

class Cat: Animal(NonFlyer()) {
    override fun makeSound() {
        println("meow!")
    }
}

interface Flyer {
    fun fly()
}

class NonFlyer: Flyer {
    override fun fly() {
        println("Can't fly")
    }
}

class HighFlyer : Flyer {
    override fun fly() {
        println("Flying high!")
    }

}

class Bird: Animal(HighFlyer()) {
    override fun makeSound() {
        println("tweet tweet!")
    }
}


fun main() {

    val animals = listOf(Dog(), Cat(), Bird())
    animals.forEach {
        it.makeSound()
        it.fly()
    }

}