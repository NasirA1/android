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
        assertEquals(0, sut.questionsLeft())
    }

    @Test fun option_selected() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()
        val question = sut.getNextQuestion()!!
        sut.selectOption(question.id, question.options.questionOptions[0])
        val selectedOption = sut.getSelectedOptions(question.id)

        assertEquals(question.options.questionOptions[0], selectedOption[0])
    }

    @Test fun single_choice_question_always_single_optoin_selected() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()
        val question = sut.getNextQuestion()!!
        sut.selectOption(question.id, question.options.questionOptions[0])
        sut.selectOption(question.id, question.options.questionOptions[1])
        sut.selectOption(question.id, question.options.questionOptions[2])
        val selectedOption = sut.getSelectedOptions(question.id)

        assertEquals(1, selectedOption.size)
        assertEquals(question.options.questionOptions[2], selectedOption[0])
    }

    @Test fun multichoice_question_multiple_optoins_can_be_selected() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        var question = sut.getNextQuestion()!!
        while(!question.options.multiChoice)
           question = sut.getNextQuestion()!!

        sut.selectOption(question.id, question.options.questionOptions[0])
        sut.selectOption(question.id, question.options.questionOptions[1])
        sut.selectOption(question.id, question.options.questionOptions[2])
        val selectedOption = sut.getSelectedOptions(question.id)

        assertEquals(3, selectedOption.size)
        assertEquals(question.options.questionOptions[0], selectedOption[0])
        assertEquals(question.options.questionOptions[1], selectedOption[1])
        assertEquals(question.options.questionOptions[2], selectedOption[2])
    }

    @Test fun multichoice_question_optoins_can_be_unselected() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        var question = sut.getNextQuestion()!!
        while(!question.options.multiChoice)
            question = sut.getNextQuestion()!!

        sut.selectOption(question.id, question.options.questionOptions[0])
        sut.selectOption(question.id, question.options.questionOptions[1])
        sut.selectOption(question.id, question.options.questionOptions[2])
        sut.unselectOption(question.id, question.options.questionOptions[1])
        val selectedOption = sut.getSelectedOptions(question.id)

        assertEquals(2, selectedOption.size)
        assertEquals(question.options.questionOptions[0], selectedOption[0])
        assertEquals(question.options.questionOptions[2], selectedOption[1])
    }

    @Test fun singlechoice_question_optoin_can_be_unselected() = runBlocking {
        val sut = QuizSession("dont care", questionRepository)

        sut.startQuiz()

        var question = sut.getNextQuestion()!!
        while(question.options.multiChoice)
            question = sut.getNextQuestion()!!

        sut.selectOption(question.id, question.options.questionOptions[0])
        sut.unselectOption(question.id, question.options.questionOptions[0])
        val selectedOption = sut.getSelectedOptions(question.id)

        assertEquals(0, selectedOption.size)
    }

}