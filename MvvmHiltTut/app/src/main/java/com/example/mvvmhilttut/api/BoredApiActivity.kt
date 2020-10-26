package com.example.mvvmhilttut.api


// http://www.boredapi.com/api/activity

data class BoredApiActivity(
    val accessibility: Int,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)