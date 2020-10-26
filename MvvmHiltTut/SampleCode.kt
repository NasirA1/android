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
import javax.inject.Qualifier
import javax.inject.Singleton


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var someInterfaceClient: SomeInterfaceClient

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


        Log.d(Constants.TAG, "onCreate: ${someInterfaceClient.callSomeInterfaces()}")
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

@Module
@InstallIn(ActivityComponent::class)
abstract class MyModule {
    @ActivityScoped
    @Binds
    abstract fun bindMyDatabase(db: MyDatabase): Database
}

@Module
@InstallIn(ApplicationComponent::class)
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


//Providing instances of the same type
interface SomeInterface {
    fun doSomething(): String
}
class SomeInterfaceImpl1 @Inject constructor(): SomeInterface {
    override fun doSomething(): String {
        return "SomeInterfaceImpl1 doSomething"
    }
}
class SomeInterfaceImpl2 @Inject constructor(): SomeInterface {
    override fun doSomething(): String {
        return "SomeInterfaceImpl2 doSomething"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

class SomeInterfaceClient @Inject constructor(
        @Impl1 private val someInterface1: SomeInterface,
        @Impl2 private val someInterface2: SomeInterface
){
    fun callSomeInterfaces(): String {
        return "\n" + someInterface1.doSomething() + "\n" + someInterface2.doSomething()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
class SomeInterfaceModule {
    @Provides
    @Impl1
    fun provideSomeInterface1(): SomeInterface =
            SomeInterfaceImpl1()
    @Impl2
    @Provides
    fun provideSomeInterface2(): SomeInterface =
            SomeInterfaceImpl2()
}

// Predefined qualifiers for App and Activity contexts respectively:
// @ApplicationContext and @ActivityContext