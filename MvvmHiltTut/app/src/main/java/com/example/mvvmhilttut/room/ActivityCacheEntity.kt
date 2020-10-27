package com.example.mvvmhilttut.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "activities")
data class ActivityCacheEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val accessibility: Double,
    val activity: String,
    val participants: Int,
    val price: Double,
    val type: String
)
