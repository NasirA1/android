package com.example.dagger2tut2.di.network.auth

import com.example.dagger2tut2.model.User
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthApi {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
    }

    @GET("users/{id}")
    suspend fun getUser( @Path("id") id: Int ): User
}