package com.example.dagger2tut2.repo

import androidx.lifecycle.LiveData
import com.example.dagger2tut2.api.BoredApi
import com.example.dagger2tut2.model.LeisureActivity
import com.example.dagger2tut2.util.Resource
import retrofit2.Retrofit
import javax.inject.Inject

class LeisureActivityRepository @Inject constructor(
    private val boredApi: BoredApi
): ILeisureActivityRepository {

    override suspend fun fetchRandomActivity(): LeisureActivity {
        return boredApi.getRandomActivity()
    }

}