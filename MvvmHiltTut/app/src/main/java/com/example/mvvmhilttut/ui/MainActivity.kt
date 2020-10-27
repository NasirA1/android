package com.example.mvvmhilttut.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmhilttut.Constants
import com.example.mvvmhilttut.R
import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.util.DataState
import com.example.mvvmhilttut.vm.MainStateEvent
import com.example.mvvmhilttut.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object { const val TAG = Constants.TAG }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetActivityEvents)
    }


    fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer {
            when(it) {
                is DataState.Success<List<ActivityModel>> -> {
                    Log.d(TAG, "subscribeObservers: $it")
                }
                is DataState.Loading -> {
                    Log.d(TAG, "subscribeObservers: Loading...")
                }
                is DataState.Error -> {
                    Log.e(TAG, "subscribeObservers: ERROR ${it.ex}")
                }
            }
        })
    }
}
