package com.example.myquiz.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquiz.MyQuiz
import com.example.myquiz.util.Constants
import kotlinx.coroutines.launch

class QuizResultViewModel @ViewModelInject constructor(
    private val quiz: MyQuiz,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun startAnotherQuiz() {
        viewModelScope.launch {
            quiz.startAnotherSession()
        }
    }

    companion object { private const val TAG = Constants.TAG }

    val quizResultState = quiz.quizResultState

}
