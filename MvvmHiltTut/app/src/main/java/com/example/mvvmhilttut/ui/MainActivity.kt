package com.example.mvvmhilttut.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object { const val TAG = Constants.TAG }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview_Content.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetActivityEvents)
        }

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetActivityEvents)
    }


    fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer {
            when(it) {
                is DataState.Success<List<ActivityModel>> -> {
                    it.data.forEach {
                        textview_Content.append(it.activity + "\n")
                    }
                    progressbar_Loading.visibility = View.GONE
                }
                is DataState.Loading -> {
                    progressbar_Loading.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    Log.e(TAG, "subscribeObservers: ERROR ${it.ex}")
                    Toast.makeText(this, "${it.ex}", Toast.LENGTH_SHORT).show()
                    progressbar_Loading.visibility = View.GONE
                }
            }
        })
    }
}
