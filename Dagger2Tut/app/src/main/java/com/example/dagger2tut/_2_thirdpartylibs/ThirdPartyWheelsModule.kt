package com.example.dagger2tut._2_thirdpartylibs

import dagger.Module
import dagger.Provides

@Module
class ThirdPartyWheelsModule {

    @Provides
    fun provideThirdPartyWheels(): ThirdPartyWheels {
        return ThirdPartyWheels()
    }

}