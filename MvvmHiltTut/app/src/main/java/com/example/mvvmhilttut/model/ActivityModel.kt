package com.example.mvvmhilttut.model


data class ActivityModel(
    val id: Int =0,
    val accessibility: Double,
    val activity: String,
    val participants: Int,
    val price: Double,
    val type: String
)
