package com.example.dagger2tut.interfaceinject

import dagger.Binds
import dagger.Module

@Module
abstract class PetrolEngineModule {

    //Binds implementation Petrol Engine to the Engine3 Interface
    @Binds
    abstract fun bindPetrolEngine(engine: PetrolEngine): Engine3

}