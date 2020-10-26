package com.example.mvvmhilttut.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmhilttut.Constants
import com.example.mvvmhilttut.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database.fetchData()

        val json = gson.toJson(
            gson.fromJson(
                    """{ subject: "Hilt", desc: "Hilt DI rocks!" }""", JsonObject::class.java
            )
        )
        Log.d(Constants.TAG, "onCreate: json:\n$json")
    }
}

interface Database {
    fun fetchData()
}

class MyDatabase @Inject constructor() : Database {
    override fun fetchData() = run {
        Log.d(Constants.TAG, "fetchData: it works!")
        Unit
    }
}

@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule {
    @ActivityScoped
    @Binds
    abstract fun bindMyDatabase(db: MyDatabase): Database
}

@InstallIn(ApplicationComponent::class)
@Module
class MyGlobalModule {
    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setPrettyPrinting().create()
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
