package com.example.dagger2tut._4_runtimeinject

import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [CoffeeModule::class])
interface CoffeeComponent {
    fun createNewCoffee(): Coffee

    @Component.Builder
    interface Builder {
        @BindsInstance fun sugar( @Named("coffee sugar") sugar: Int ): Builder
        @BindsInstance fun milk( @Named("coffee milk") milk: Int ): Builder
        fun build(): CoffeeComponent
    }
}
