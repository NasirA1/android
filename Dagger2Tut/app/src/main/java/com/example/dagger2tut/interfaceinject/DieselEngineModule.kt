package com.example.dagger2tut.interfaceinject

import dagger.Binds
import dagger.Module

@Module
abstract class DieselEngineModule {

    //Binds implementation DieselEngine to the Engine3 Interface
    @Binds
    abstract fun bindPetrolEngine(engine: DieselEngine): Engine3

}