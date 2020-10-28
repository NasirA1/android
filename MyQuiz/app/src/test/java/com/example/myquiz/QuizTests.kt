package com.example.myquiz

import com.example.myquiz.model.QuizSession
import com.example.myquiz.repo.InMemoryQuestionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*


class QuizTests {

    private val questionRepository = InMemoryQuestionRepository()

    @Test
    fun quiz_session_initialised_with_name() {
        val sut = QuizSession("Tommy", questionRepository)
        assertEquals("Tommy", sut.playerName)
    }

    @Test fun when_quiz_session_started_questions_loaded() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)
        sut.start()
        assertTrue(sut.questionsAvailable() > 0)
        assertTrue(sut.questionsAvailable() >= QuizSession.QuestionsPerQuizSession)
    }

    @Test fun when_quiz_session_started_quiz_questions_loaded() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)
        sut.start()
        assertTrue(sut.quizQuestionCount() == QuizSession.QuestionsPerQuizSession)
    }

    @Test fun when_quiz_session_started_currentIndex_pointing_to_first_question() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)
        sut.start()
        assertEquals(0, sut.currentIndex)
    }

    @Test fun when_quiz_session_started_first_question_returned() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)
        sut.start()
        val question = sut.getNextQuestion()
        println(question)
        assertNotNull(question)
    }

    @Test fun when_quiz_session_started_first_question_returned_currentIndex_incremented() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)
        sut.start()
        sut.getNextQuestion()
        assertEquals(1, sut.currentIndex)
    }

}