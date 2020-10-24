package com.example.dagger2tut._5_singleton.di

import com.example.dagger2tut._5_singleton.LogcatUi
import com.example.dagger2tut._5_singleton.UserInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LogcatUiModule {

    @Singleton
    @Binds
    abstract fun bindUserInterface(ui: LogcatUi): UserInterface
}