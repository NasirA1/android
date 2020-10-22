package com.example.logindialogtut

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

enum class LoginResult {
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
    LOGIN_CANCEL
}

interface LoginDialogListener {
    fun loginComplete(loginResult: LoginResult)
}

class LoginDialog(private val loginDialogListener: LoginDialogListener) : AppCompatDialogFragment() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var viewModel: LoginDialogViewModel

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.layout_login_dialog, null)!!
        initialiseControls(view)
        initialiseViewModel()
        return buildDialog(view)
    }

    private fun initialiseControls(view: View) {
        editTextUsername = view.findViewById(R.id.edit_username)
        editTextPassword = view.findViewById(R.id.edit_password)
        progressBarLoading = view.findViewById(R.id.progress_login)
    }

    private fun initialiseViewModel() {
        val factory = Injector.provideLoginViewModelFactory(context!!)
        viewModel = ViewModelProviders.of(this, factory).get(LoginDialogViewModel::class.java)

        viewModel.isLoading.observe(this, Observer { onLoadingChanged(it) })
        viewModel.loginSuccess.observe(this, Observer { onLoginResult(it) })
    }

    private fun onLoginResult(success: Boolean) {
        Log.d(Config.TAG,"login attempt result=$success")
        loginDialogListener.loginComplete(
            if(success) LoginResult.LOGIN_SUCCESS
            else LoginResult.LOGIN_FAILURE
        )
    }

    private fun onLoadingChanged(loadingStatus: Boolean) {
        Log.d(Config.TAG,"loadingStatus=$loadingStatus")
        if(loadingStatus) {
            progressBarLoading.visibility = View.VISIBLE
        } else {
            progressBarLoading.visibility = View.GONE
            //close the dialog when loading completes
            dismiss()
        }
    }

    private fun buildDialog(view: View): AlertDialog {
        val builder = AlertDialog.Builder(activity)

        val dlg = builder.setView(view)
            .setTitle("Login")
            .setNegativeButton("cancel") { dialogInterface, i ->
                loginDialogListener.loginComplete(LoginResult.LOGIN_CANCEL)
            }
            .setPositiveButton("ok", null).create()  //null overrides custom dialog close behaviour on button click

        //we don't want to close the dialog right away, we want to wait for onLoadingChanged(loadingStatus==false)
        dlg.setOnShowListener { dlg ->
            dlg as AlertDialog
            val button = dlg.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                viewModel.login(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }

        return dlg
    }
}