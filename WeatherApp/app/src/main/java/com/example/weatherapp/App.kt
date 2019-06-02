package com.example.weatherapp

import android.app.Application
import android.util.Log


class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        Log.d(APP_TITLE, "Initialising App...")
        super.onCreate()
        instance = this
        Log.d(APP_TITLE, "App Initialised")
    }

}
