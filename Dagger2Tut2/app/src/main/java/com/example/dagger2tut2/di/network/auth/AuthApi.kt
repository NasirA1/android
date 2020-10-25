package com.example.dagger2tut2.di.network.auth

import okhttp3.ResponseBody
import retrofit2.http.GET


interface AuthApi {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @GET
    suspend fun getFakeStuff(): ResponseBody
}