package com.example.dagger2tut._5_singleton

import android.util.Log
import com.example.dagger2tut.AppConfig.TAG
import javax.inject.Inject

class LogcatUi @Inject constructor() : UserInterface {

    override fun displayData(businessObject: BusinessObject) {
        Log.d(TAG, "displayData: $businessObject")
    }
}