package com.otkritie.hackaton.screens.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otkritie.hackaton.data.remote.model.messages.MessageData
import com.otkritie.hackaton.data.remote.websocket.WebSocketListener
import com.otkritie.hackaton.data.repository.ChatRepository
import com.otkritie.hackaton.domain.mapper.toViewRenderer
import com.otkritie.hackaton.domain.model.MessageViewRenderer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("myId")

    private val _list = MutableLiveData<List<MessageViewRenderer>>()
    val list: LiveData<List<MessageViewRenderer>> = _list

    var message = ""

    private val _clearMessageEvent = Channel<String>()
    val clearMessageEvent = _clearMessageEvent.receiveAsFlow()

    fun getHistory() {
        viewModelScope.launch {
            id?.let {
                chatRepository.getHistory(id,
                    onSuccess = {
                        _list.value = it?.messages?.map { it.toViewRenderer(chatRepository.id) }
                    },
                    onFailure = {

                    })
            }

            chatRepository.connect(WebSocketListener {
                viewModelScope.launch {
                    Log.e("Message", it.toString())
                    val message = it.toViewRenderer(chatRepository.id)
                    _list.value = listOf(message) + (list.value ?: emptyList())
                }
            })
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            id?.let {
                if (message.isNotEmpty()) {
                    Log.e("ID", chatRepository.id.toString())
                    chatRepository.sendMessage(
                        id, message,
                        onSuccess = {
                            message = ""
                            _clearMessageEvent.trySend("")
                        },
                        onFailure = {}
                    )
                }
            }
        }
    }

    override fun onCleared() {
        chatRepository.disconnect()
    }
}
