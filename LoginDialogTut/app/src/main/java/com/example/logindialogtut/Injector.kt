package com.example.logindialogtut

import android.content.Context
import kotlinx.coroutines.delay


object Injector {

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val loginRepository = LoginRepository.getInstance(
            //Just return a mock inMemory Dao for now..
            object: LoginDao {
                override suspend fun login(username: String, passwordHash: String): Boolean {
                    delay(1000)
                    return username == "user" && passwordHash == "password"
                }

            }
        )
        return LoginViewModelFactory(loginRepository)
    }

}