package com.example.mvvmhilttut.di

import com.example.mvvmhilttut.api.BoredApiClient
import com.example.mvvmhilttut.api.BoredApiClient.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideBoredApi(retrofit: Retrofit.Builder) =
        retrofit
            .build()
            .create(BoredApiClient::class.java)
}
