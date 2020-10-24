package com.example.dagger2tut._1_basics

import com.example.dagger2tut.MainActivity
import dagger.Component


//This is the car injector component
@Component
interface CarComponent {
    fun createNewCar(): Car
    fun fieldInject(mainActivity: MainActivity)
}
