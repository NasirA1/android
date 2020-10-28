package com.example.myquiz.di

import com.example.myquiz.MyQuiz
import com.example.myquiz.repo.InMemoryQuestionRepository
import com.example.myquiz.repo.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class QuizModule {

    @Singleton
    @Provides
    fun provideQuiz(questionRepository: QuestionRepository) =
        MyQuiz(questionRepository)

    @Singleton
    @Provides
    fun provideQuestionRepository(): QuestionRepository = InMemoryQuestionRepository()
}