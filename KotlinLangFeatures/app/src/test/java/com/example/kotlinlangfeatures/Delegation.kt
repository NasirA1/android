package com.example.kotlinlangfeatures

interface BirdInterface {
    fun flyAndFindFood()
}

class Eagle(val age: Int): BirdInterface {
    override fun flyAndFindFood() {
        println("I am an eagle. I am $age years old. I am flying and finding food")
    }
}

class Cuckoo(bird: BirdInterface): BirdInterface by bird {
    override fun flyAndFindFood() {
        println("I am a Cuckoo. I am flying and finding food!")
    }
}


fun main() {
    val eagle = Eagle(2)
    eagle.flyAndFindFood()
    val cuckoo = Cuckoo(eagle)
    cuckoo.flyAndFindFood()
}