package com.example.myquiz.repo

import com.example.myquiz.model.Question
import com.example.myquiz.model.QuestionOption
import com.example.myquiz.model.QuestionOptions

class InMemoryQuestionRepository: QuestionRepository {

    private val questionsDatabase = listOf(
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
            question = "د لاندنيو څخه كومې د كمپيوټر پروگرامينگ ژبې دي؟",
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
            question = "د تركيې پلازمېنه كومه ده؟",
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
            question = "كلكولس چا كشف كړ؟",
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
            question = "د تاو شوي 'ډي اېن اې' يوې ټوټې ته څه ويل كيږي؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "كروموزوم"),
                    QuestionOption(2, "جين"),
                    QuestionOption(3, "حجره"),
                    QuestionOption(4, "انساج"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(1)
        ),
        Question(
            id = 9,
            question = "نباتات د كومې پروسې له لارې د لمر رڼا په خواړو تبديلوي؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "اوكسيډايزېشن"),
                    QuestionOption(2, "احتراق"),
                    QuestionOption(3, "فوټوسېنتيسيز"),
                    QuestionOption(4, "آيونايزېشن"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(3)
        ),
        Question(
            id = 10,
            question = "كمپيوټر چا اختراع كړ؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "الن ټيورينگ"),
                    QuestionOption(2, "بيل گېټس"),
                    QuestionOption(3, "سټيف جوبز"),
                    QuestionOption(4, "چارلز بابېج"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
        Question(
            id = 11,
            question = "په عادي تودوخه كښې كوم فلزونه مايع بڼه لري؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "وسپنه"),
                    QuestionOption(2, "سيماب"),
                    QuestionOption(3, "سيزيم"),
                    QuestionOption(4, "مگنيزيم"),
                    QuestionOption(5, "پوټاسيم"),
                    QuestionOption(6, "ميلانين")
                ),
                multiChoice = true
            ),
            correctAnswers = listOf(2, 3)
        ),
        Question(
            id = 12,
            question = "څه شى د اټم د هستې پر شاوخوا څرخ وهي؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    QuestionOption(1, "پروټون"),
                    QuestionOption(2, "نيوټرون"),
                    QuestionOption(3, "پازيټرون"),
                    QuestionOption(4, "الېكټرون"),
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
    )


    override suspend fun getAllQuestionIds(): List<Int> = questionsDatabase.map { it.id }

    override suspend fun getQuestion(id: Int): Question = questionsDatabase.first { it.id == id }
}
