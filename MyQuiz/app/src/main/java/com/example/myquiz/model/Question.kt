package com.example.myquiz.model

import com.example.myquiz.repo.QuestionRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

data class QuestionOption(
    val id: Int,
    val description: String
)

data class QuestionOptions(
    val questionOptions: List<QuestionOption>,
    val multiChoice: Boolean
)

data class Question(
    val id: Int = 0,
    val question: String,
    val options: QuestionOptions,
    val correctAnswers: List<Int>
)

class QuizSession @Inject constructor(
    val playerName: String,
    private val questionRepository: QuestionRepository
) {
    private lateinit var questionIds: List<Int>

    companion object {
        const val QuestionsPerQuizSession = 10
    }

    fun questionsAvailable(): Int =
        if(this::questionIds.isInitialized)
            questionIds.size
        else 0

    fun start() {
        runBlocking {
            questionIds = questionRepository.getAllQuestionIds()
            println("Loaded questions: $questionIds")
            println("Count: ${questionsAvailable()}")
        }
    }
}
