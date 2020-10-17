package com.example.coroutinetut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Launching coroutine..")
        getRandomActivity()
        Log.d(TAG, "Launching coroutine..DONE")

        background.setOnClickListener { getRandomActivity() }

    }

    private fun getRandomActivity() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val result = rpcGetRandomActivity()
                withContext(Dispatchers.Main) {
                    this@MainActivity.textView.text = result
                    Log.d(TAG, result)
                }
            }
        }
    }

    private suspend fun rpcGetRandomActivity(): String {
        return coroutineScope {
            URL("http://www.boredapi.com/api/activity").readText()
        }
    }
}