package com.example.designpatterns.console

import kotlin.random.Random
import kotlin.random.nextInt

abstract class GameWeapon

class Stone : GameWeapon()
class Sword : GameWeapon()
class Pistol: GameWeapon()

class RandomWeaponFactory {

    private val weapons = listOf(
        "com.example.designpatterns.console.Stone",
        "com.example.designpatterns.console.Sword",
        "com.example.designpatterns.console.Pistol"
    )

    fun createRandomWeapon(): GameWeapon {
        val i = Random.nextInt(weapons.indices)
        val cls = weapons[i]
        val obj = Class.forName(cls).newInstance() as GameWeapon
        return obj
    }
}

fun main() {
    val factory = RandomWeaponFactory()
    println(factory.createRandomWeapon())
    println(factory.createRandomWeapon())
    println(factory.createRandomWeapon())
}