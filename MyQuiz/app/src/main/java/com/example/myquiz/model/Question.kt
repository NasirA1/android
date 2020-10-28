package com.example.myquiz.model

data class Question(
    val id: Int = 0,
    val question: String,
    val options: QuestionOptions,
    val correctAnswers: List<Int>
)

data class QuestionOptions(
    val questionOptions: List<String>,
    val multiChoice: Boolean
)
