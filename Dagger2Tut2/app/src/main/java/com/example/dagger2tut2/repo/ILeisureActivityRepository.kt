package com.example.dagger2tut2.repo

import com.example.dagger2tut2.model.LeisureActivity

interface ILeisureActivityRepository {
    suspend fun fetchRandomActivity(): LeisureActivity
}