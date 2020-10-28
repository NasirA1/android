package com.example.myquiz.model

data class Question(
    val id: Int = 0,
    val question: String,
    val options: QuestionOptions,
    val correctAnswers: List<Int>
)

data class QuestionOption(
    val id: Int,
    val description: String
)

data class QuestionOptions(
    val questionOptions: List<QuestionOption>,
    val multiChoice: Boolean
)
