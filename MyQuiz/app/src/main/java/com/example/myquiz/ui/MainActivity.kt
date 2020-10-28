package com.example.myquiz.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myquiz.R
import com.example.myquiz.ui.fragments.StartQuizFragment
import com.example.myquiz.util.Constants
import com.example.myquiz.util.DataState
import com.example.myquiz.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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
                is DataState.Success -> { onSuccessState(it) }
                is DataState.Error -> { Log.e(TAG, "MainActivity quizStartState ERROR: ${it.ex}") }
            }
        })

        viewModel.currentQuestionState.observe(this, Observer {
            Log.d(TAG, "subscribeObservers: progress = $it")
            //TODO find out why the progressbar UI is not updating..
            progress_quiz_progress.progress = it
        })
    }

    private fun onSuccessState(dataState: DataState.Success<String>) {
        layout_header.visibility = View.VISIBLE
        progress_quiz_progress.max = viewModel.totalQuestions
        textview_player_name.text = dataState.data
    }

}
