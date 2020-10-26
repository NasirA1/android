package com.example.dagger2tut2.di

import android.app.Activity
import android.app.Application
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.dagger2tut2.R
import com.example.dagger2tut2.api.BoredApi
import com.example.dagger2tut2.api.BoredApi.Companion.BASE_URL
import com.example.dagger2tut2.repo.ILeisureActivityRepository
import com.example.dagger2tut2.repo.LeisureActivityRepository
import com.example.dagger2tut2.ui.MainActivity
import com.example.dagger2tut2.vm.MainViewModel
import com.example.dagger2tut2.vm.MainViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions()
            .placeholder(R.drawable.background)
            .error(R.drawable.background)

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager =
        Glide.with(application)
            .setDefaultRequestOptions(requestOptions)

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application) =
        ContextCompat.getDrawable(application, R.drawable.logo)!!

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideBoredApi(retrofit: Retrofit): BoredApi =
        retrofit.create(BoredApi::class.java)


    @Singleton
    @Provides
    fun provideLeisureRepository(boredApi: BoredApi): ILeisureActivityRepository =
        LeisureActivityRepository(boredApi)


    @Provides
    fun provideMainViewModelFactory(repository: LeisureActivityRepository) =
        MainViewModelFactory(repository)

}