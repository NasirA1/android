package com.example.dagger2tut._4_runtimeinject

import dagger.Module
import dagger.Provides

@Module
class TeaModule(private val sugar: Int) {

    @Provides
    fun provideTea(): Tea = Tea(sugar)
}