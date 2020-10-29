package com.example.myquiz.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myquiz.MyQuiz
import com.example.myquiz.util.Constants


class MainViewModel @ViewModelInject constructor(
    private val quiz: MyQuiz,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    val quizStartState = quiz.quizStartState

    fun totalQuestions() = quiz.totalQuestions()

    val currentQuestionState = quiz.currentQuestionState

}
