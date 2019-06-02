package com.example.weatherapp

import android.os.Bundle
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread


val APP_TITLE: String get() = "WEATHERAPP"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        doAsync {
            Log.d(APP_TITLE, "Starting forecast request...")
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { toast(message = it.date.toDateString()) }
                longToast("ForecastByZipCodeRequest Completed")
                Log.d(APP_TITLE, "ForecastByZipCodeRequest Completed")
            }
        }

    }
}


fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
