package com.example.myquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myquiz.model.Question
import com.example.myquiz.model.QuizResult
import com.example.myquiz.model.QuizResultListener
import com.example.myquiz.model.QuizSession
import com.example.myquiz.repo.QuestionRepository
import com.example.myquiz.util.DataState
import javax.inject.Inject


class MyQuiz @Inject constructor(
    private val questionRepository: QuestionRepository
): QuizResultListener {

    private var quizSession: QuizSession? = null

    private var _quizStartState = MutableLiveData<DataState<String>>()
    val quizStartState = _quizStartState as LiveData<DataState<String>>

    private var _fetchQuestionState = MutableLiveData<DataState<Question?>>()
    val fetchQuestionState = _fetchQuestionState as LiveData<DataState<Question?>>

    private var _currentQuestionState = MutableLiveData<Int>()
    val currentQuestionState = _currentQuestionState as LiveData<Int>

    private var _quizResultState = MutableLiveData<QuizResult>()
    val quizResultState = _quizResultState as LiveData<QuizResult>

    fun totalQuestions() = quizSession?.questionsCount() ?: 0

    suspend fun startQuiz(playerName: String) {
        _quizStartState.value = DataState.Loading
        try {
            quizSession = QuizSession(playerName, questionRepository, this)
            quizSession?.startQuiz()
            _currentQuestionState.value = 0
            _quizStartState.value = DataState.Success(playerName)
        } catch(ex: Throwable) {
            _quizStartState.value = DataState.Error(ex)
        }
    }


    suspend fun fetchQuestion() {
        _fetchQuestionState.value = DataState.Loading
        try {
            val question = quizSession?.getNextQuestion()
            if(question != null) {
                _currentQuestionState.value = quizSession?.currentQuestionIndex?.plus(1)
            }
            _fetchQuestionState.value = DataState.Success(question)
        } catch(ex: Throwable) {
            _fetchQuestionState.value = DataState.Error(ex)
        }
    }

    fun setAnswerOption(option: String, selected: Boolean) {
        if(selected) {
            quizSession?.selectAnswerOption(option)
        } else {
            quizSession?.unselectAnswerOption(option)
        }
    }

    override fun quizSessionEnded(result: QuizResult) {
        _quizResultState.postValue(result)
    }

    fun onLastQuestion(): Boolean =
        quizSession?.questionsLeft() == 0


    suspend fun startAnotherSession() {
        startQuiz(quizSession?.playerName!!)
    }

}