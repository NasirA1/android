package com.example.myquiz.di

import com.example.myquiz.Quiz
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
        Quiz(questionRepository)

    @Singleton
    @Provides
    fun provideQuestionRepository(): QuestionRepository = InMemoryQuestionRepository()
}