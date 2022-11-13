package com.otkritie.hackaton.data.repository

import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.remote.datasource.ChatRemoteDataSource
import com.otkritie.hackaton.data.remote.model.history.GetHistoryResponse
import com.otkritie.hackaton.data.remote.model.send.SendMessageRequest
import com.otkritie.hackaton.data.remote.websocket.WebSocketClient
import com.otkritie.hackaton.data.remote.websocket.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val authPreference: AuthPreference,
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val webSocketClient: WebSocketClient
) {

    val id = authPreference.id

    suspend fun getHistory(id: Int, onSuccess: (GetHistoryResponse?) -> Unit, onFailure: (String) -> Unit) {
        chatRemoteDataSource.getHistory(id,onSuccess,onFailure)
    }

    suspend fun sendMessage(dialog: Int, text: String, onSuccess: (String?) -> Unit, onFailure: (String) -> Unit) {
        chatRemoteDataSource.sendMessage(dialog, text, onSuccess, onFailure)
    }

    fun connect(listener: WebSocketListener) {
        webSocketClient.connect(listener)
    }

    fun disconnect() = webSocketClient.disconnect()
}
