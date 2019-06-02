package com.example.weatherapp

public interface Command<out T> {
    fun execute(): T
}
