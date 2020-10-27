package com.example.kotlinlangfeatures

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking


// Sealed classes give the flexibility of abstract classes(different param types) plus the
// the restrictiveness of enum classes (compile time errors for unhandled when cases)
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    class Success<T>(val data: T): Result<T>()
    class Error(val ex: Throwable): Result<Nothing>()
}


suspend fun webRequest(): Flow<Result<String>> = flow {
    emit(Result.Loading)
    try {
        emit(Result.Success("data fetched!"))
    }catch(ex: Throwable) {
        emit(Result.Error(ex))
    }
}


fun main() = runBlocking {

    webRequest().onEach {
        when (it) {
            is Result.Loading -> {
                println("Show progressbar here!")
            }
            is Result.Success -> {
                println("Data arrived: ${it.data}")
            }
            is Result.Error -> {
                println("ERROR: ${it.ex}")
            }
        }
    }

    Unit
}
