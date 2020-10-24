package com.example.dagger2tut._4_runtimeinject

import javax.inject.Inject
import javax.inject.Named

interface ICoffee

data class Coffee @Inject constructor(
    @Named("coffee sugar") val sugar: Int,
    @Named("coffee milk") val milk: Int
) : ICoffee
