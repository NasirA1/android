package com.example.dagger2tut2.di

import androidx.lifecycle.ViewModelProvider
import com.example.dagger2tut2.vm.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(
        vmProviderFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

}