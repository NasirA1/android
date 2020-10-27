package com.example.mvvmhilttut.vm

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mvvmhilttut.Constants
import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.repo.MainRepository
import com.example.mvvmhilttut.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<List<ActivityModel>>>()
    private val _purgeState = MutableLiveData<DataState<Unit>>()

    val dataState: LiveData<DataState<List<ActivityModel>>> = _dataState
    val purgeState: LiveData<DataState<Unit>> = _purgeState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetActivityEvents -> {
                    mainRepository.getActivity().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.PurgeCache -> {
                    mainRepository.purgeCache().onEach { purgeState ->
                        _purgeState.value = purgeState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // no-op
                    Log.d(Constants.TAG, "setStateEvent: MainStateEvent.None")
                }
            }
        }
    }
}

sealed class MainStateEvent {

    object None: MainStateEvent()
    object GetActivityEvents: MainStateEvent()
    object PurgeCache: MainStateEvent()

}