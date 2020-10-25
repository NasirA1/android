package com.example.dagger2tut2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.dagger2tut2.ui.auth.AuthActivity
import com.example.dagger2tut2.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer { authResource ->
            when(authResource.status) {
                AuthResource.AuthStatus.LOADING -> Unit
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    Log.d(AuthActivity.TAG, "subscribeObservers: LOGIN SUCCESS - ${authResource.data?.email}")
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> navLoginScreen()
                AuthResource.AuthStatus.ERROR -> {
                    Log.e(AuthActivity.TAG, "subscribeObservers: ERROR - ${authResource.message}")
                    Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun navLoginScreen() {
        Intent(this, AuthActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}