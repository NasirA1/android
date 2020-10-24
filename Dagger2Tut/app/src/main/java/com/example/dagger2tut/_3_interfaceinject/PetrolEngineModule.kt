package com.example.dagger2tut._3_interfaceinject

import dagger.Binds
import dagger.Module

@Module
abstract class PetrolEngineModule {

    //Binds implementation Petrol Engine to the Engine3 Interface
    @Binds
    abstract fun bindPetrolEngine(engine: PetrolEngine): Engine3

}