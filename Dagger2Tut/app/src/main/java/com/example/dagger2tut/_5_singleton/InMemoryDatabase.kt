package com.example.dagger2tut._5_singleton

import javax.inject.Inject

class InMemoryDatabase @Inject constructor() : Database {
    override fun getData(): BusinessObject {
        return BusinessObject("hello", 1)
    }
}