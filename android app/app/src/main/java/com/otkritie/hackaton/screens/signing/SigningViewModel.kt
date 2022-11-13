package com.otkritie.hackaton.screens.signing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otkritie.hackaton.core.RequestState
import com.otkritie.hackaton.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigningViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var login = ""

    var password = ""

    private val _requestState = MutableLiveData<RequestState>()
    val requestState: LiveData<RequestState> = _requestState

    fun login() {
        viewModelScope.launch {
            _requestState.value = RequestState.Loading
            repository.authorize(login, password,
                onSuccess = {
                    _requestState.value = RequestState.Success
                },
                onFailure = {
                    _requestState.value = RequestState.Failure(it)
                }
            )
        }
    }
}


