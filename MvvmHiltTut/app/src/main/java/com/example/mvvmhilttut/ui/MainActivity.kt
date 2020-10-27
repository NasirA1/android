package com.example.mvvmhilttut.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmhilttut.Constants
import com.example.mvvmhilttut.R
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


    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            onDataStateChange(dataState) {
                (dataState as DataState.Success).let { successState ->
                    textview_Content.append("\n")
                    successState.data.forEach {
                        textview_Content.append(it.activity + "\n")
                    }
                }
            }
        })

        viewModel.purgeState.observe(this, Observer { dataState ->
            onDataStateChange(dataState) {
                textview_Content.text = ""
            }
        })
    }

    private fun<T> onDataStateChange(it: DataState<T>, successHandler: ()->Unit) {
        when (it) {
            is DataState.Success<T> -> {
                successHandler.invoke()
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
    }

    private fun areYouSure(message: String, onYes: () -> Unit, onNo: (() -> Unit)? = null) =
        AlertDialog.Builder(this)
            .setTitle("Confirm")
            .setMessage(message)
            .setPositiveButton("Yes") { _, _ -> onYes.invoke() }
            .setNegativeButton("No") { _, _ -> onNo?.invoke() }
            .show()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_purge -> {
                areYouSure("All data will be erased! Are you sure?", {
                    viewModel.setStateEvent(MainStateEvent.PurgeCache)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
