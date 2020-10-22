package com.example.logindialogtut

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

interface FragmentLoggedOutListener {
    fun onLoginSuccess()
    fun onLoginCancel()
    fun onLoginFailure()
}

class FragmentLoggedOut(private val listener: FragmentLoggedOutListener) : Fragment(R.layout.fragment_loggedout), LoginDialogListener {

    private lateinit var buttonLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!
        buttonLogin = view.findViewById(R.id.button_login)
        buttonLogin.setOnClickListener { showLoginDialog() }
        return view
    }

    private fun showLoginDialog() {
        val dlg = LoginDialog(this)
        dlg.show(activity?.supportFragmentManager!!, "Login")
    }

    override fun loginComplete(loginResult: LoginResult) {
        Log.d(Config.TAG, "loginResult=$loginResult")

        when(loginResult) {
            LoginResult.LOGIN_SUCCESS -> listener.onLoginSuccess()
            LoginResult.LOGIN_FAILURE -> listener.onLoginFailure()
            LoginResult.LOGIN_CANCEL -> listener.onLoginCancel()
        }
    }

}
