package com.example.dagger2tut2.vm

import androidx.lifecycle.*
import com.example.dagger2tut2.model.LeisureActivity
import com.example.dagger2tut2.repo.ILeisureActivityRepository
import com.example.dagger2tut2.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(
    private val leisureActivityRepository: ILeisureActivityRepository
) : ViewModel() {

    private val leisureActivity = MutableLiveData<Resource<LeisureActivity>>()

    fun fetchRandomActivity() {
        viewModelScope.launch(CoroutineExceptionHandler { _, ex ->
            leisureActivity.value = Resource.Error(ex.message!!)
        })
        {
            leisureActivity.value = Resource.Loading()
            val res = leisureActivityRepository.fetchRandomActivity()
            leisureActivity.value = Resource.Success(res)
        }
    }

    fun getLeisureActivity() = leisureActivity as LiveData<Resource<LeisureActivity>>
}


class MainViewModelFactory(private val leisureActivityRepository: ILeisureActivityRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(leisureActivityRepository) as T
    }
}