package com.example.dagger2tut._2_thirdpartylibs

import dagger.Component


@Component(modules = [ThirdPartyWheelsModule::class])
interface Car2Component {

    fun createNewCar2(): Car2

}