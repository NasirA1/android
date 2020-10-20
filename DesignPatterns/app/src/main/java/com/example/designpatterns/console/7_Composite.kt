package com.example.designpatterns.console

interface Shape {
    fun draw()
}

class Circle : Shape {
    override fun draw() { println("Drawing Circle") }
}

class Square : Shape {
    override fun draw() { println("Drawing Square") }
}

class Triangle : Shape {
    override fun draw() { println("Drawing Triangle") }
}

class Canvas {
    private val shapes = mutableListOf<Shape>()

    fun addShape(shape: Shape) = shapes.add(shape)

    fun draw() =
        shapes.forEach { it.draw() }
}

fun main() {

    Canvas().run {
        addShape(Circle())
        addShape(Square())
        addShape(Triangle())
        this
    }.draw()

}