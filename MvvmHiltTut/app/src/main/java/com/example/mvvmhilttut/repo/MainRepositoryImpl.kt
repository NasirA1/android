package com.example.mvvmhilttut.repo

import com.example.mvvmhilttut.api.ApiActivityMapper
import com.example.mvvmhilttut.api.BoredApiClient
import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.room.ActivityDao
import com.example.mvvmhilttut.room.CacheActivityMapper
import com.example.mvvmhilttut.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val boredApiClient: BoredApiClient,
    private val activityDao: ActivityDao,
    private val cacheMapper: CacheActivityMapper,
    private val apiMapper: ApiActivityMapper
) : MainRepository {

    override suspend fun getActivity(): Flow<DataState<List<ActivityModel>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val activityEntity = boredApiClient.getRandomActivity()
            val activityModel = apiMapper.mapFromEntity(activityEntity)
            val r = activityDao.insert(cacheMapper.mapToEntity(activityModel))
            val cachedActivities = activityDao.getAllActivities()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedActivities)))
        } catch (ex: Throwable) {
            emit(DataState.Error(ex))
        }
    }

}