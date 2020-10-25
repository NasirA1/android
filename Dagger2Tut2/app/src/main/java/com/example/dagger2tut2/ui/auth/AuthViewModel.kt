package com.example.dagger2tut2.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dagger2tut2.Constants
import com.example.dagger2tut2.di.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        Log.d(TAG, "AuthViewModel: $authApi")
    }

}