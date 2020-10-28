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
    private lateinit var quizQuestions: List<Question>
    private val answers = mutableMapOf<Int, MutableList<String>>()

    companion object {
        const val QuestionsPerQuizSession = 10
    }


    var currentQuestionIndex: Int = -1
        private set


    fun questionsCount(): Int =
        if (this::quizQuestions.isInitialized)
            quizQuestions.size
        else -1


    fun questionsLeft() = questionsCount() - (currentQuestionIndex + 1)


    suspend fun startQuiz() = withContext(Dispatchers.IO) {
            questionIds = questionRepository.getAllQuestionIds().shuffled()
            println("All questions: $questionIds")
            println("All questions count: ${questionIds.size}")
            quizQuestions = questionIds.slice(0 until QuestionsPerQuizSession).map { questionRepository.getQuestion(it) }
            //println("Selected questions for quiz: $quizQuestions")
            println("Selected questions for quiz count: ${questionsCount()}")
            currentQuestionIndex = -1
        }


    suspend fun getNextQuestion(): Question? = withContext(Dispatchers.IO) {
        currentQuestionIndex++
        if (currentQuestionIndex < quizQuestions.size) {
            println("currentQuestionIndex=$currentQuestionIndex")
            quizQuestions[currentQuestionIndex]
        } else {
            currentQuestionIndex--
            println("Quiz ended at index: currentQuestionIndex=$currentQuestionIndex")
            null
        }
    }

    fun selectAnswerOption(option: String) = selectAnswerOption(quizQuestions[currentQuestionIndex].id, option)

    fun getSelectedAnswerOptions() = getSelectedAnswerOptions(quizQuestions[currentQuestionIndex].id)

    fun unselectAnswerOption(option: String) = unselectAnswerOption(quizQuestions[currentQuestionIndex].id, option)


    private fun selectAnswerOption(questionId: Int, option: String) {
        if(answers[questionId] == null) {
            answers[questionId] = mutableListOf()
        }
        if(!quizQuestions[currentQuestionIndex].options.multiChoice) {
            answers[questionId]!!.clear()
        }
        answers[questionId]!!.add(option)
    }

    private fun getSelectedAnswerOptions(questionId: Int) =
        answers[questionId] as List<String>

    private fun unselectAnswerOption(questionId: Int, option: String) {
        if(answers[questionId] != null) {
            answers[questionId]!!.remove(option)
        }
    }

}
