package com.example.myquiz.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myquiz.Quiz
import com.example.myquiz.util.Constants
import com.example.myquiz.util.DataState
import kotlinx.coroutines.launch

class StartQuizViewModel @ViewModelInject constructor(
    private val quiz: Quiz,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    val quizStartState = quiz.quizStartState


    fun startQuiz(playerName: String) {
        viewModelScope.launch {
            quiz.startQuiz(playerName)
        }
    }

}
