package com.example.dagger2tut2.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2tut2.Constants
import com.example.dagger2tut2.SessionManager
import com.example.dagger2tut2.di.network.auth.AuthApi
import com.example.dagger2tut2.model.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    companion object { private const val TAG = Constants.TAG }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        Log.d(TAG, "AuthViewModel: $authApi")
    }

    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "authenticateWithId: Attempting to login..")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        val authUser = MutableLiveData<AuthResource<User>>()
        viewModelScope.launch(CoroutineExceptionHandler { _, ex ->
            Log.e(TAG, "AuthViewModel: ERROR $ex")
            authUser.value = AuthResource.error(ex.message.toString(), User.unauthenticatedUser())
        }) {
            val user = authApi.getUser(userId)
            Log.d(TAG, "AuthViewModel: Got user: $user")
            authUser.value = AuthResource.authenticated(user)
        }
        return authUser
    }

    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()

}