package com.example.kotlinlangfeatures


//1. Generic class example
class MyTriple<A: Any, B: Any, C: Any>(
    val first: A,
    val second: B,
    val third: C
) {
    fun printTypes() {
        println("The type of first is ${first::class.java.typeName}")
        println("The type of second is ${second::class.java.typeName}")
        println("The type of third is ${third::class.java.typeName}")
    }
}


//2. Works with any Number type
fun <T: Number> List<T>.numberFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for(item in this)
        if(predicate(item))
            result.add(item)
    return result
}

fun <T> List<T>.println() = println(this)

fun String.println() = println(this)


fun main() {

    (1..10).toList().numberFilter { it > 5 }.println()
    (1..10).map { it.toDouble() }.numberFilter { it > 5.0 }.println()
//    arrayOf("hello", "goodbye").toList()
//        .numberFilter { it.length() > 5 }.println() //doesn't compile. Array must be a number container

    MyTriple(1, "hello", true).printTypes()


}