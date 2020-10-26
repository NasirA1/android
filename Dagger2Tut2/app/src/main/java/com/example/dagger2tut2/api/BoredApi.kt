package com.example.dagger2tut2.api

import com.example.dagger2tut2.model.LeisureActivity
import retrofit2.http.GET

interface BoredApi {
    companion object {
        const val BASE_URL = "http://www.boredapi.com/api/"
    }

    @GET("activity")
    suspend fun getRandomActivity(): LeisureActivity
}