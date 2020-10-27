package com.example.mvvmhilttut.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activityCacheEntity: ActivityCacheEntity): Long

    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): List<ActivityCacheEntity>

    @Query("DELETE FROM activities")
    suspend fun purge()
}
