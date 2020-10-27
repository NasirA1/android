package com.example.mvvmhilttut.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmhilttut.room.ActivityCacheDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideActivitiesDatabase(@ApplicationContext context: Context): ActivityCacheDatabase =
        Room.databaseBuilder(
            context,
            ActivityCacheDatabase::class.java,
            ActivityCacheDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideActivityDao(db: ActivityCacheDatabase) =
        db.activitiesDao()

}