package com.example.dagger2tut._5_singleton.di

import com.example.dagger2tut._5_singleton.Database
import com.example.dagger2tut._5_singleton.InMemoryDatabase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class InMemoryDatabaseModule {

    @Singleton
    @Binds
    abstract fun provideDatabase(db: InMemoryDatabase): Database
}