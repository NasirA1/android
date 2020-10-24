package com.example.dagger2tut._3_interfaceinject


import android.util.Log
import com.example.dagger2tut.AppConfig.TAG
import javax.inject.Inject


class Car3 @Inject constructor(
    private val wheels: Wheels3,
    private val engine: Engine3
) {

    fun drive() {
        Log.d(TAG, "Car3 driving!..")
        engine.run()
        wheels.rotate()
    }
}

interface Engine3 {
    fun run()
}

class PetrolEngine @Inject constructor() : Engine3 {
    override fun run() {
        Log.d(TAG, "run: PetrolEngine running..")
    }
}

class DieselEngine @Inject constructor() : Engine3 {
    override fun run() {
        Log.d(TAG, "run: DieselEngine running..")
    }
}

class Wheels3 @Inject constructor() {
    fun rotate() {
        Log.d(TAG, "rotate: Wheels3 rotating..")
    }
}
