package com.example.logindialogtut

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class LoginDialogViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private var loading = MutableLiveData<Boolean>()
    private var loginResult = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loading.value = true
            loginResult.value = loginRepository.login(username, password)
            loading.value = false
        }
    }

    val isLoading: LiveData<Boolean> = loading

    val loginSuccess: LiveData<Boolean> = loginResult
}



class LoginViewModelFactory(private val loginRepository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginDialogViewModel(loginRepository) as T
    }
}
