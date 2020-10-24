package com.example.dagger2tut._2_thirdpartylibs

import android.util.Log
import com.example.dagger2tut.AppConfig.TAG
import javax.inject.Inject


class Car2 @Inject constructor(
    private val wheels: ThirdPartyWheels,
    private val engine: Engine2
) {

    fun drive() {
        Log.d(TAG, "Car2 driving!..")
        engine.run()
        wheels.rotate()
    }
}

class Engine2 @Inject constructor() {

    fun run() {
        Log.d(TAG, "run: Engine2 running..")
    }

}

//suppose this is a third party class and we cannot modify its constructor to allow it to be injected via Dagger
class ThirdPartyWheels {
    fun rotate() {
        Log.d(TAG, "rotate: ThirdPartyWheels rotating..")
    }
}
