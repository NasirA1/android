package com.example.mvvmhilttut.util

sealed class DataState<out R> {

    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val ex: Throwable): DataState<Nothing>()
    object Loading: DataState<Nothing>()

}