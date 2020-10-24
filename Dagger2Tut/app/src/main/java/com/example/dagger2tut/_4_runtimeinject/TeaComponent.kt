package com.example.dagger2tut._4_runtimeinject

import dagger.Component

@Component(modules = [TeaModule::class])
interface TeaComponent {
    fun createNewTea(): Tea
}