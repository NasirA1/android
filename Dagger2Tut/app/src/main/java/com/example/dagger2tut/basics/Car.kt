package com.example.dagger2tut.basics

import android.util.Log
import com.example.dagger2tut.AppConfig.TAG
import javax.inject.Inject


class Car @Inject constructor(
    private val wheels: Wheels
) {
    //field injection
    @Inject lateinit var engine: Engine

    fun drive() {
        Log.d(TAG, "Car driving!..")
        engine.run()
        wheels.rotate()
    }

    //method injection
    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }
}

class Remote @Inject constructor() {
    fun setListener(car: Car) {
        Log.d(TAG, "setListener: Remote connected to $car")
    }
}

class Engine @Inject constructor(private val cambelt: Cambelt) {

    fun run() {
        Log.d(TAG, "run: Engine running..")
        cambelt.turn()
    }

}

class Wheels @Inject constructor() {
    fun rotate() = Unit
}

class Cambelt @Inject constructor() {

    fun turn() {
        Log.d(TAG, "turn: Cambelt turning..")
    }

}