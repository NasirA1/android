package com.example.myquiz.model

interface QuizResultListener {
    fun quizSessionEnded(result: QuizResult)
}

data class QuizResult(val totalQuestions: Int, val correctAnswers: Int) {
    fun percentage() =
        correctAnswers.toDouble() / totalQuestions.toDouble() * 100.0
}
