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
        assertEquals(-1, sut.questionsLeft())
    }

    @Test fun when_quiz_session_started_questions_loaded() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        assertEquals(QuizSession.QuestionsPerQuizSession, sut.questionsLeft())
    }

    @Test fun when_quiz_session_started_quiz_questions_loaded() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        assertTrue(sut.questionsCount() == QuizSession.QuestionsPerQuizSession)
    }

    @Test fun when_quiz_session_started_first_question_returned() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()
        val question = sut.getNextQuestion()
        println(question)

        assertNotNull(question)
    }

    @Test fun when_quiz_session_started_first_question_returned_currentIndex_incremented() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()
        sut.getNextQuestion()

        assertEquals(0, sut.currentQuestionIndex)
        assertEquals(9, sut.questionsLeft())
    }

    @Test fun when_quiz_session_started_second_question_returned_currentIndex_updated() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()
        sut.getNextQuestion()
        sut.getNextQuestion()

        assertEquals(1, sut.currentQuestionIndex)
        assertEquals(8, sut.questionsLeft())
    }

    @Test fun when_quiz_session_questions_finished_null_returned() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        for(i in 0 until sut.questionsCount())
            sut.getNextQuestion()

        assertNull(sut.getNextQuestion())
    }

}