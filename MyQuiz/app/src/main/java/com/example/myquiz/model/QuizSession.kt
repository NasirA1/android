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

    companion object {
        const val QuestionsPerQuizSession = 10
    }


    var currentQuestionIndex = -1
        private set


    fun questionsCount(): Int =
        if (this::quizQuestionIds.isInitialized)
            quizQuestionIds.size
        else -1


    fun questionsLeft() = questionsCount() - (currentQuestionIndex + 1)


    suspend fun startQuiz() = withContext(Dispatchers.IO) {
            questionIds = questionRepository.getAllQuestionIds().shuffled()
            println("All questions: $questionIds")
            println("All questions count: ${questionIds.size}")
            quizQuestionIds = questionIds.slice(0 until QuestionsPerQuizSession)
            println("Selected questions for quiz: $quizQuestionIds")
            println("Selected questions for quiz count: ${quizQuestionIds.size}")
        }


    suspend fun getNextQuestion(): Question? = withContext(Dispatchers.IO) {
        if (currentQuestionIndex < quizQuestionIds.size - 2) {
            val current = quizQuestionIds[++currentQuestionIndex]
            questionRepository.getQuestion(current)
        } else {
            null
        }
    }

}
