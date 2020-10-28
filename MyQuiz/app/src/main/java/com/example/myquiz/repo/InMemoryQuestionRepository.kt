package com.example.myquiz.repo

import com.example.myquiz.model.Question
import com.example.myquiz.model.QuestionOption
import com.example.myquiz.model.QuestionOptions

class InMemoryQuestionRepository: QuestionRepository {

    private val questions = listOf(
        Question(
            id = 1,
            question = "د افغانستان پخوانۍ پلازمېنه كومه ده؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "هرات"),
                    QuestionOption(2, "كندهار"),
                    QuestionOption(3, "كابل"),
                    QuestionOption(4, "مزار"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 2,
            question = "د نړۍ تر ټولو ستر هېواد كوم يو دى؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "كاناډا"),
                    QuestionOption(2, "امريكا"),
                    QuestionOption(3, "چين"),
                    QuestionOption(4, "روسيه"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
        Question(
            id = 3,
            question = "د لاندو څخه كومې يې د كمپيوټر د پروگرامينگ ژبې دي؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "A++"),
                    QuestionOption(2, "Ada"),
                    QuestionOption(3, "Linux"),
                    QuestionOption(4, "Windows"),
                    QuestionOption(5, "Ruby"),
                ),
                multiChoice = true
            ),
            correctAnswers = listOf(2, 5)
        ),
        Question(
            id = 4,
            question = "د تركيې پلازمېنه څه شى ده؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "استانبول"),
                    QuestionOption(2, "قونيه"),
                    QuestionOption(3, "انقره"),
                    QuestionOption(4, "تهران"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(3)
        ),
        Question(
            id = 5,
            question = "د كلكولس رياضي چا كشف كړل؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "اسحق نيوټن"),
                    QuestionOption(2, "البرټ آينشټاين"),
                    QuestionOption(3, "محمد الخوارزمي"),
                    QuestionOption(4, "لايبنيڅ"),
                ),
                multiChoice = true
            ),
            correctAnswers = listOf(1, 4)
        ),
        Question(
            id = 6,
            question = "د مځكې او لمر تر مېنځ واټن څومره دى؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "اته رڼايي ثانيې"),
                    QuestionOption(2, "اته رڼايي دقيقې"),
                    QuestionOption(3, "اتيا رڼايي ثانيې"),
                    QuestionOption(4, "يونيم ميليون كيلومتره"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 7,
            question = "د رڼا سرعت په تشيال كښې څومره دى؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "درې لكه متره په ثانيه كښې"),
                    QuestionOption(2, "درې زره كيلومتره په دقيقه كښې"),
                    QuestionOption(3, "درې ميليونه كيلومتره په ساعت كښې"),
                    QuestionOption(4, "درې لكه كيلومتره په ثانيه كښې"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(1)
        ),
        Question(
            id = 8,
            question = "What is the capital of Afghanistan?",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "Kandahar"),
                    QuestionOption(2, "Kabul"),
                    QuestionOption(3, "Herat"),
                    QuestionOption(4, "Mazar"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 9,
            question = "What is the capital of Afghanistan?",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "Kandahar"),
                    QuestionOption(2, "Kabul"),
                    QuestionOption(3, "Herat"),
                    QuestionOption(4, "Mazar"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 10,
            question = "What is the capital of Afghanistan?",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "Kandahar"),
                    QuestionOption(2, "Kabul"),
                    QuestionOption(3, "Herat"),
                    QuestionOption(4, "Mazar"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 11,
            question = "What is the capital of Afghanistan?",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "Kandahar"),
                    QuestionOption(2, "Kabul"),
                    QuestionOption(3, "Herat"),
                    QuestionOption(4, "Mazar"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 12,
            question = "What is the capital of Afghanistan?",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "Kandahar"),
                    QuestionOption(2, "Kabul"),
                    QuestionOption(3, "Herat"),
                    QuestionOption(4, "Mazar"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
    )

    override suspend fun getAllQuestionIds(): List<Int> = questions.map { it.id }


    override suspend fun getQuestion(id: Int): Question = questions[id]
}
