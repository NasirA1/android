package com.example.dagger2tut.runtimeinject

import javax.inject.Inject


data class Tea @Inject constructor(val sugar: Int)
