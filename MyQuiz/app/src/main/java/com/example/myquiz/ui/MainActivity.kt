package com.example.myquiz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myquiz.R
import com.example.myquiz.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object { const val TAG = Constants.TAG }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


class SomeClass @Inject constructor() {
    fun doSomething() = "it works!"
}
