package com.example.mvvmhilttut.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmhilttut.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//Hilt supports different scopes
/**
 @Singleton                         Application
 @ActivityRetainedScope             ViewModel
 @ActivityScoped                    Activity
 @FragmentScoped                    Fragment
 @ViewScoped                        View
 @ServiceScoped                     ServiceScoped
*/
