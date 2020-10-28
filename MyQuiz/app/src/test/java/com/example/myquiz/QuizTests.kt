package com.example.myquiz

import com.example.myquiz.model.QuizSession
import com.example.myquiz.repo.InMemoryQuestionRepository
import org.junit.Test

import org.junit.Assert.*




class QuizTests {

    private val questionRepository = InMemoryQuestionRepository()

    @Test
    fun quiz_session_initialised_with_name() {
        val sut = QuizSession("Tommy", questionRepository)
        assertEquals("Tommy", sut.playerName)
    }

    @Test fun when_quiz_session_started_questions_loaded() {
        val sut = QuizSession("Tommy", questionRepository)
        sut.start()
        assertTrue(sut.questionsAvailable() > 0)
        assertTrue(sut.questionsAvailable() >= QuizSession.QuestionsPerQuizSession)
    }

}