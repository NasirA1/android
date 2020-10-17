package com.example.retrofittut.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CatFactsService {

    companion object {
        const val BASE_URL = "http://cat-fact.herokuapp.com/"
    }

    @GET("facts/random/")
    suspend fun getCatFact(): Response<CatFact>

}