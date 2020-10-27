package com.example.mvvmhilttut.repo

import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.util.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getActivity(): Flow<DataState<List<ActivityModel>>>
    suspend fun purgeCache(): Flow<DataState<Unit>>
}