package com.example.myquiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myquiz.MyApplication_HiltComponents
import com.example.myquiz.R
import com.example.myquiz.util.Constants
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
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
