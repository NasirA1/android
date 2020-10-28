package com.example.myquiz.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myquiz.Quiz
import com.example.myquiz.util.Constants


class MainViewModel @ViewModelInject constructor(
    private val quiz: Quiz,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    val quizStartState = quiz.quizStartState

}
