package com.example.mvvmhilttut.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ActivityCacheEntity::class], version = 2)
abstract class ActivityCacheDatabase: RoomDatabase() {

    abstract fun activitiesDao(): ActivityDao

    companion object {
        val DATABASE_NAME = "activities.db"
    }
}
