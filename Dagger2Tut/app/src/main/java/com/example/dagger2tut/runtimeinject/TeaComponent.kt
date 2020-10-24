package com.example.dagger2tut.runtimeinject

import dagger.Component

@Component(modules = [TeaModule::class])
interface TeaComponent {
    fun createNewTea(): Tea
}