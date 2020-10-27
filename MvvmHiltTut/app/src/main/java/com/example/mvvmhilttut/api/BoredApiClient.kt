package com.example.mvvmhilttut.api

import retrofit2.http.GET


interface BoredApiClient {

    companion object {
        const val BASE_URL = "http://www.boredapi.com/api/"
    }

    @GET("activity")
    suspend fun getRandomActivity(): ActivityEntity
}