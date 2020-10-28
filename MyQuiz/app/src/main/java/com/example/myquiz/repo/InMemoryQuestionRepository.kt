package com.example.myquiz.repo

import com.example.myquiz.model.Question
import com.example.myquiz.model.QuestionOptions

class InMemoryQuestionRepository: QuestionRepository {

    private val questionsDatabase = listOf(
        Question(
            id = 1,
            question = "د افغانستان پخوانۍ پلازمېنه كومه ده؟",
            options = QuestionOptions(
                questionOptions = listOf("هرات", "كندهار", "كابل", "مزار"),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 2,
            question = "د نړۍ تر ټولو ستر هېواد كوم يو دى؟",
            options = QuestionOptions(
                questionOptions = listOf("كاناډا", "امريكا", "چين", "روسيه"),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
        Question(
            id = 3,
            question = "د لاندنيو څخه كومې د كمپيوټر پروگرامينگ ژبې دي؟",
            options = QuestionOptions(
                questionOptions = listOf("++A", "Ada", "Linux", "Windows", "Ruby"),
                multiChoice = true
            ),
            correctAnswers = listOf(2, 5)
        ),
        Question(
            id = 4,
            question = "د تركيې پلازمېنه كومه ده؟",
            options = QuestionOptions(
                questionOptions = listOf("استانبول", "قونيه", "انقره", "تهران"),
                multiChoice = false
            ),
            correctAnswers = listOf(3)
        ),
        Question(
            id = 5,
            question = "كلكولس چا كشف كړ؟",
            options = QuestionOptions(
                questionOptions = listOf("اسحق نيوټن", "البرټ آينشټاين", "محمد الخوارزمي", "لايبنيڅ"),
                multiChoice = true
            ),
            correctAnswers = listOf(1, 4)
        ),
        Question(
            id = 6,
            question = "د مځكې او لمر تر مېنځ واټن څومره دى؟",
            options = QuestionOptions(
                questionOptions = listOf("اته رڼايي ثانيې", "اته رڼايي دقيقې", "اتيا رڼايي ثانيې", "يونيم ميليون كيلومتره"),
                multiChoice = false
            ),
            correctAnswers = listOf(2)
        ),
        Question(
            id = 7,
            question = "د رڼا سرعت په تشيال كښې څومره دى؟",
            options = QuestionOptions(
                questionOptions = listOf(
                    "درې لكه متره په ثانيه كښې",
                    "درې زره كيلومتره په دقيقه كښې",
                    "درې ميليونه كيلومتره په ساعت كښې",
                    "درې لكه كيلومتره په ثانيه كښې"
                ),
                multiChoice = false
            ),
            correctAnswers = listOf(1)
        ),
        Question(
            id = 8,
            question = "د تاو شوي 'ډي اېن اې' يوې ټوټې ته څه ويل كيږي؟",
            options = QuestionOptions(
                questionOptions = listOf("كروموزوم", "جين", "حجره", "انساج"),
                multiChoice = false
            ),
            correctAnswers = listOf(1)
        ),
        Question(
            id = 9,
            question = "نباتات د كومې پروسې له لارې د لمر رڼا په خواړو تبديلوي؟",
            options = QuestionOptions(
                questionOptions = listOf("اوكسيډايزېشن", "احتراق", "فوټوسېنتيسيز", "آيونايزېشن"),
                multiChoice = false
            ),
            correctAnswers = listOf(3)
        ),
        Question(
            id = 10,
            question = "كمپيوټر چا اختراع كړ؟",
            options = QuestionOptions(
                questionOptions = listOf("الن ټيورينگ", "بيل گېټس", "سټيف جوبز", "چارلز بابېج"),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
        Question(
            id = 11,
            question = "په عادي تودوخه كښې كوم فلزونه مايع بڼه لري؟",
            options = QuestionOptions(
                questionOptions = listOf("وسپنه", "سيماب", "سيزيم", "مگنيزيم", "پوټاسيم", "ميلانين"),
                multiChoice = true
            ),
            correctAnswers = listOf(2, 3)
        ),
        Question(
            id = 12,
            question = "څه شى د اټم د هستې پر شاوخوا څرخ وهي؟",
            options = QuestionOptions(
                questionOptions = listOf("پروټون", "نيوټرون", "پازيټرون", "الېكټرون"),
                multiChoice = false
            ),
            correctAnswers = listOf(4)
        ),
    )


    override suspend fun getAllQuestionIds(): List<Int> = questionsDatabase.map { it.id }

    override suspend fun getQuestion(id: Int): Question = questionsDatabase.first { it.id == id }
}
