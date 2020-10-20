package com.example.designpatterns.console

import kotlin.math.sqrt


open class RoundPeg(open val radius: Double)

class RoundHole(val radius: Double) {
    fun fits(peg: RoundPeg): Boolean {
        return radius >= peg.radius
    }
}


class SquarePeg(val width: Double)

class SquarePegAdapter(val squarePeg: SquarePeg) : RoundPeg(0.0) {
    override val radius:Double = squarePeg.width * sqrt(2.0) / 2.0
}

fun main() {

    val hole = RoundHole(5.0)
    val rpeg = RoundPeg(5.0)
    println("Hole fits round peg?  ${hole.fits(rpeg)}")

    val small_sqpeg = SquarePeg(5.0)
    val large_sqpeg = SquarePeg(10.0)
    //hole.fits(small_sqpeg)  //this won't compile (incompatible types)

    //so we use adapters
    val small_sqpeg_adapter = SquarePegAdapter(small_sqpeg)
    val large_sqpeg_adapter = SquarePegAdapter(large_sqpeg)
    println("Hole fits small square peg?  ${hole.fits(small_sqpeg_adapter)}")
    println("Hole fits large square peg?  ${hole.fits(large_sqpeg_adapter)}")
}