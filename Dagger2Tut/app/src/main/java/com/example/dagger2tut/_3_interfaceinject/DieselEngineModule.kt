package com.example.dagger2tut._3_interfaceinject

import dagger.Binds
import dagger.Module

@Module
abstract class DieselEngineModule {

    //Binds implementation DieselEngine to the Engine3 Interface
    @Binds
    abstract fun bindPetrolEngine(engine: DieselEngine): Engine3

}