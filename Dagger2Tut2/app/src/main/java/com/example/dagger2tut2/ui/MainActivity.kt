package com.example.dagger2tut2.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dagger2tut2.Constants
import com.example.dagger2tut2.R
import com.example.dagger2tut2.model.LeisureActivity
import com.example.dagger2tut2.util.Resource
import com.example.dagger2tut2.vm.MainViewModel
import com.example.dagger2tut2.vm.MainViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseViewModel()
        container.setOnClickListener {  viewModel.fetchRandomActivity()  }
        viewModel.fetchRandomActivity()
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getLeisureActivity().observe(this, Observer {
            onLeisureActivityFetched(it)
        })
    }

    private fun onLeisureActivityFetched(it: Resource<LeisureActivity>) {
        when (it) {
            is Resource.Loading -> progress_bar.visibility = View.VISIBLE
            is Resource.Success -> {
                progress_bar.visibility = View.GONE
                textview_activity.text = it.data?.activity
            }
            is Resource.Error -> {
                progress_bar.visibility = View.GONE
                Log.e(Constants.TAG, "ERROR: ${it.message}")
                Toast.makeText(this, "ERR: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}