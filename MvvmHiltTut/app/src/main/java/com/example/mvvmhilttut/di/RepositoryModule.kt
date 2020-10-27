package com.example.mvvmhilttut.di

import com.example.mvvmhilttut.api.ApiActivityMapper
import com.example.mvvmhilttut.api.BoredApiClient
import com.example.mvvmhilttut.repo.MainRepository
import com.example.mvvmhilttut.room.ActivityDao
import com.example.mvvmhilttut.room.CacheActivityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        activityDao: ActivityDao,
        activityApi: BoredApiClient,
        cacheActivityMapper: CacheActivityMapper,
        apiActivityMapper: ApiActivityMapper
    ) =
        MainRepository(
            activityDao = activityDao,
            boredApiClient = activityApi,
            apiMapper = apiActivityMapper,
            cacheMapper = cacheActivityMapper
        )

}