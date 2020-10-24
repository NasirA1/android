package com.example.dagger2tut._4_runtimeinject

import javax.inject.Inject


data class Tea @Inject constructor(val sugar: Int)
