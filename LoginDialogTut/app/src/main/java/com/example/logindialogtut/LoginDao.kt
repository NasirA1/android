package com.example.logindialogtut

interface LoginDao {
    suspend fun login(username: String, passwordHash: String): Boolean
}
