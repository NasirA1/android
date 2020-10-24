package com.example.dagger2tut._4_runtimeinject

import dagger.Binds
import dagger.Module


@Module
abstract class CoffeeModule {

    @Binds
    abstract fun bindCoffee(coffee: Coffee): ICoffee
}