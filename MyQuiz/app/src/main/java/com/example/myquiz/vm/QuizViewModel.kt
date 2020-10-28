package com.example.myquiz.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquiz.MyQuiz
import com.example.myquiz.util.Constants
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(
    private val quiz: MyQuiz,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    val fetchQuestionState = quiz.fetchQuestionState


    fun fetchNextQuestion() {
        viewModelScope.launch {
            quiz.fetchQuestion()
        }
    }

    fun setAnswerOption(option: String, selected: Boolean) {
        quiz.setAnswerOption(option, selected)
    }

}
