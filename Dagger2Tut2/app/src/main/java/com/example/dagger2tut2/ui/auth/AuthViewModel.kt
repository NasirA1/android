package com.example.dagger2tut2.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dagger2tut2.AppConfig
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    companion object { private const val TAG = AppConfig.TAG }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
    }

}