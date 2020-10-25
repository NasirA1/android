package com.example.dagger2tut2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.dagger2tut2.Constants
import com.example.dagger2tut2.R
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
        Log.d(TAG, "onCreate: AuthActivity loading..")

        viewModel = ViewModelProviders.of(this, vmProviderFactory).get(AuthViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}