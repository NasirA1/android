package com.example.myquiz.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myquiz.R
import com.example.myquiz.ui.fragments.StartQuizFragment
import com.example.myquiz.util.Constants
import com.example.myquiz.util.DataState
import com.example.myquiz.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object { const val TAG = Constants.TAG }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.quizStartState.observe(this, Observer {
            when (it) {
                is DataState.Loading -> { Log.d(TAG, "MainActivity quizStartState Loading..") }
                is DataState.Success -> { Log.d(TAG, "MainActivity quizStartState STARTED") }
                is DataState.Error -> { Log.e(TAG, "MainActivity quizStartState ERROR: ${it.ex}") }
            }
        })
    }

}
