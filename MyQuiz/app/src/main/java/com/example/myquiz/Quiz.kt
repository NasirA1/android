package com.example.myquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

}