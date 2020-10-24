package com.example.dagger2tut._5_singleton

import android.util.Log
import com.example.dagger2tut.AppConfig.TAG
import javax.inject.Inject


class BusinessLogic @Inject constructor(
    private val ui: UserInterface,
    private val db: Database
) {

    fun doSomething() {
        Log.d(TAG, "doSomething: ui=${ui.hashCode()} db=${db.hashCode()}")
        val data = db.getData()
        ui.displayData(data)
        val result = data.copy(num = data.num + 1)
        ui.displayData(result)
    }

}