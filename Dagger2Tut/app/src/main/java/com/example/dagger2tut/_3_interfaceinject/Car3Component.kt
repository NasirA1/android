package com.example.dagger2tut._3_interfaceinject

import dagger.Component


@Component(modules = [DieselEngineModule::class])       //injects diesel engine
//@Component(modules = [PetrolEngineModule::class])     //engines petrol engine
interface Car3Component {

    fun createNewCar3(): Car3

}