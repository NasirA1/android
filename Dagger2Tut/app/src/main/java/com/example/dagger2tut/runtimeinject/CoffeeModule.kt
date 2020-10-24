package com.example.dagger2tut.runtimeinject

import dagger.Binds
import dagger.Module


@Module
abstract class CoffeeModule {

    @Binds
    abstract fun bindCoffee(coffee: Coffee): ICoffee
}