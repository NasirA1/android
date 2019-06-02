package com.example.weatherapp



data class Employee(val firstName: String, val lastName: String, val age: Int, val salary: Float) {
    fun print() = println(this)
}


fun sum(xs: Array<Int>): Int =  xs.reduce { total, next -> total + next }


fun main()
{
    var emp: Employee?
    emp = Employee("Nasir", "Ahmad", 55, 34000.05F)
    emp.print()

    emp = null
    emp?.print()

    emp?.print() ?: println("emp is null")

    try {
        emp!!.print()
    }catch(ex: kotlin.KotlinNullPointerException) {
        println("Not Null assertion failed. emp == null")
    }

    val xs = arrayOf(1, 2, 3, 4)
    println(sum(xs))
}
