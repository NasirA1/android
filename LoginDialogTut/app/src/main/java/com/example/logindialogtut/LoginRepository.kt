package com.example.logindialogtut


class LoginRepository private constructor(private val loginDao: LoginDao) {

    companion object : SingletonHolder<LoginRepository, LoginDao>(::LoginRepository)

    suspend fun login(username: String, passwordHash: String): Boolean = loginDao.login(username, passwordHash)

}