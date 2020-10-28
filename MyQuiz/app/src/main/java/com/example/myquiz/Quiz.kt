package com.example.myquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myquiz.model.Question
import com.example.myquiz.model.QuizSession
import com.example.myquiz.repo.QuestionRepository
import com.example.myquiz.util.DataState
import javax.inject.Inject

class Quiz @Inject constructor(
    private val questionRepository: QuestionRepository
) {

    private var quizSession: QuizSession? = null

    private var _quizStartState = MutableLiveData<DataState<Unit>>()
    val quizStartState = _quizStartState as LiveData<DataState<Unit>>

    private var _fetchQuestionState = MutableLiveData<DataState<Question?>>()
    val fetchQuestionState = _fetchQuestionState as LiveData<DataState<Question?>>


    suspend fun startQuiz(playerName: String) {
        _quizStartState.value = DataState.Loading
        try {
            quizSession = QuizSession(playerName, questionRepository)
            quizSession?.startQuiz()
            _quizStartState.value = DataState.Success(Unit)
        } catch(ex: Throwable) {
            _quizStartState.value = DataState.Error(ex)
        }
    }


    suspend fun fetchQuestion() {
        _fetchQuestionState.value = DataState.Loading
        try {
            val question = quizSession?.getNextQuestion()
            _fetchQuestionState.value = DataState.Success(question)
        } catch(ex: Throwable) {
            _fetchQuestionState.value = DataState.Error(ex)
        }
    }

}