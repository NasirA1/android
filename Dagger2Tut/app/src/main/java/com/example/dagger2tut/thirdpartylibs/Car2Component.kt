package com.example.dagger2tut.thirdpartylibs

import dagger.Component


@Component(modules = [ThirdPartyWheelsModule::class])
interface Car2Component {

    fun createNewCar2(): Car2

}