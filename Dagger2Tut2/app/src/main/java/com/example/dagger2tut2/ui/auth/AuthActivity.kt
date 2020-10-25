package com.example.dagger2tut2.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.dagger2tut2.Constants
import com.example.dagger2tut2.R
import com.example.dagger2tut2.model.User
import com.example.dagger2tut2.ui.main.MainActivity
import com.example.dagger2tut2.vm.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.auth_activity.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object { const val TAG = Constants.TAG }
    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager
    @Inject lateinit var vmProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        login_button.setOnClickListener { attemptLogin() }

        Log.d(TAG, "onCreate: AuthActivity loading..")
        viewModel = ViewModelProviders.of(this, vmProviderFactory).get(AuthViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(AuthViewModel::class.java) //new 2020 way!

        viewModel.observeAuthState().observe(this, Observer { onUserAuthentication(it) })

        setLogo()
    }

    private fun onUserAuthentication(authResource: AuthResource<User>) {
        Log.d(TAG, "onUserAuthentication: $authResource")
        when(authResource.status) {
            AuthResource.AuthStatus.LOADING -> progress_bar.visibility = View.VISIBLE
            AuthResource.AuthStatus.AUTHENTICATED -> {
                progress_bar.visibility = View.GONE
                Log.d(TAG, "onUserAuthentication: LOGIN SUCCESS - ${authResource.data?.email}")
                onLoginSuccess()
            }
            AuthResource.AuthStatus.NOT_AUTHENTICATED -> progress_bar.visibility = View.GONE
            AuthResource.AuthStatus.ERROR -> {
                Log.e(TAG, "onUserAuthentication: ERROR - ${authResource.message}")
                Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
                progress_bar.visibility = View.GONE
            }
        }
    }

    private fun attemptLogin() {
        val userId = user_id_input.text.toString().trim()
        if(userId.isEmpty()) return
        viewModel.authenticateWithId(userId.toInt())
    }

    private fun onLoginSuccess() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}