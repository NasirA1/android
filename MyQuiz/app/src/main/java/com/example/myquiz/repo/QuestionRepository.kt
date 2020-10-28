package com.example.myquiz.repo

import com.example.myquiz.model.Question

interface QuestionRepository {
    suspend fun getAllQuestionIds(): List<Int>
    suspend fun getQuestion(id: Int): Question
}