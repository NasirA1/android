package com.example.logindialogtut

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentLoggedOutListener {

    private val fragmentLoggedOut = FragmentLoggedOut(this)
    private val fragmentLoggedIn = FragmentLoggedIn()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.layout_contents, fragmentLoggedOut)
            .commit()
    }

    override fun onLoginSuccess() {
        supportFragmentManager.beginTransaction().replace(
            R.id.layout_contents, fragmentLoggedIn)
            .commit()
    }

    override fun onLoginCancel() {
        Log.w(Config.TAG, "LOGIN was cancelled")
    }

    override fun onLoginFailure() {
        Log.e(Config.TAG, "Oops! login failed!")
    }


}