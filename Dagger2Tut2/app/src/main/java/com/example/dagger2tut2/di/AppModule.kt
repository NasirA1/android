package com.example.dagger2tut2.di

import android.app.Application
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.dagger2tut2.R
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
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}