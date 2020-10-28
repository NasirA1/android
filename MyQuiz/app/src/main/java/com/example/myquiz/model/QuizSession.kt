package com.example.myquiz.model

import com.example.myquiz.repo.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class QuizSession @Inject constructor(
    val playerName: String,
    private val questionRepository: QuestionRepository
) {
    private lateinit var questionIds: List<Int>
    private lateinit var quizQuestionIds: List<Int>

    var currentIndex = -1
        private set

    companion object {
        const val QuestionsPerQuizSession = 10
    }

    fun questionsAvailable(): Int =
        if(this::questionIds.isInitialized)
            questionIds.size
        else 0

    suspend fun start() = withContext(Dispatchers.IO) {
        questionIds = questionRepository.getAllQuestionIds().shuffled()
        println("All questions: $questionIds")
        println("All questions count: ${questionsAvailable()}")
        quizQuestionIds = questionIds.slice(0 until QuestionsPerQuizSession)
        println("Selected questions for quiz: $quizQuestionIds")
        println("Selected questions for quiz count: ${quizQuestionIds.size}")
        currentIndex = 0
    }

    fun quizQuestionCount(): Int = quizQuestionIds.size

    suspend fun getNextQuestion(): Question? = withContext(Dispatchers.IO) {
        if (currentIndex < quizQuestionIds.size - 1) {
            val current = quizQuestionIds[currentIndex++]
            questionRepository.getQuestion(current)
        } else {
            null
        }
    }

}
