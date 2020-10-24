package com.example.dagger2tut._5_singleton.di

import com.example.dagger2tut._5_singleton.BusinessLogic
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InMemoryDatabaseModule::class, LogcatUiModule::class])
interface BusinessLogicComponent {

    fun createNewBusinessLogic(): BusinessLogic
}