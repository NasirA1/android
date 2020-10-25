package com.example.dagger2tut2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger2tut2.model.User
import com.example.dagger2tut2.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResource<User>>()

    companion object { const val TAG = Constants.TAG }


    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cachedUser
    }

    fun logout() {
        Log.d(TAG, "logout: logging out!")
        cachedUser.value = AuthResource.logout()
    }

}