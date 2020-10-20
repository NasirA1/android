package com.example.designpatterns.console

import java.util.*

// Useful when you need to create a large number of similar objects
// To reduce memory usage you share objects that are similar in some way rather than always
// creating new ones

// Steps
// 1. split state into intrinsic and extrinsic
// 2. add extrinsic state to operations
// 3. use factory to create leaves

abstract class FlyWeight {
    abstract fun extrinsicStateFunction(extrinsicStateVar1: Int, extrinsicStateVar2: Int)
}

class FlyWeightImpl(private val intrinsicStateVar: String) : FlyWeight() {
    override fun extrinsicStateFunction(extrinsicStateVar1: Int, extrinsicStateVar2: Int) {
        println("FlyWeightImpl extrinsicStateVar1=$extrinsicStateVar1, extrinsicStateVar2=$extrinsicStateVar2, intrinsicStateVar=$intrinsicStateVar")
    }
}

class FlyWeightFactory {

    private val flyweights = mutableMapOf<String, FlyWeight>()

    fun getFlyWeight(intrinsicStateVar: String): FlyWeight {
        if(flyweights.containsKey(intrinsicStateVar)) {
            println("Returning the cached flyweight {$intrinsicStateVar}")
            return flyweights[intrinsicStateVar]!!
        }

        println("Instantiating new flyweight {$intrinsicStateVar}")
        val flyweight = FlyWeightImpl(intrinsicStateVar)
        flyweights[intrinsicStateVar] = flyweight
        return flyweight
    }

}


fun main() {
    val factory = FlyWeightFactory()

    var obj = factory.getFlyWeight("heavy-resource")
    obj.extrinsicStateFunction(2, 45)

    //somewhere else
    obj = factory.getFlyWeight("heavy-resource")
    obj.extrinsicStateFunction(-34, 104)
}
