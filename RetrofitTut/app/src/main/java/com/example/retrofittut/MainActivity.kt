package com.example.retrofittut

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofittut.api.CatFactsService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()

        layoutGenerateNewFact.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData() {
        tvContent.visibility = View.INVISIBLE
        tvTimestamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(CatFactsService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactsService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCatFact()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.text)

                    withContext(Dispatchers.Main) {
                        tvContent.visibility = View.VISIBLE
                        tvTimestamp.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        tvContent.text = data.text
                        tvTimestamp.text = data.createdAt
                    }
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "ERROR: Unable to fetch cat fact!",
                        Toast.LENGTH_LONG
                    ).show()
                    e.printStackTrace()
                }
            }
        }
    }
}