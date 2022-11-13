package com.otkritie.hackaton.screens.chatmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otkritie.hackaton.core.RequestState
import com.otkritie.hackaton.data.repository.ChatMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatMenuViewModel @Inject constructor(
    private val repository: ChatMenuRepository
) : ViewModel() {

    private var _id = 0
    val id get() = _id

    private val _requestEvent = MutableLiveData<RequestState>()
    val requestEvent: LiveData<RequestState> = _requestEvent

    fun requestDialog() {
        viewModelScope.launch {
            _requestEvent.value = RequestState.Loading
            repository.getDialogId(
                onSuccess = {
                    _requestEvent.value = RequestState.Success
                    _id = it
                },
                onFailure = {
                    _requestEvent.value = RequestState.Failure(it)
                }
            )
        }
    }
}
