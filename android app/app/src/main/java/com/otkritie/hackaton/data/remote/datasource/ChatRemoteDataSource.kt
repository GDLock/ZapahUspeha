package com.otkritie.hackaton.data.remote.datasource

import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.datasource.delegate.RemoteDataSourceDelegate
import com.otkritie.hackaton.data.remote.model.history.GetHistoryResponse
import com.otkritie.hackaton.data.remote.model.send.SendMessageDataRequest
import com.otkritie.hackaton.data.remote.model.send.SendMessageRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRemoteDataSource @Inject constructor(
    private val chatApi: ChatApi,
    private val delegate: RemoteDataSourceDelegate
) {

    suspend fun getHistory(id: Int, onSuccess: (GetHistoryResponse?) -> Unit, onFailure: (String) -> Unit) {
        delegate.sendRequest(
            request = { chatApi.getHistory(id, 50) },
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    suspend fun sendMessage(dialog: Int, text: String, onSuccess: (String?) -> Unit, onFailure: (String) -> Unit) {
        delegate.sendRequest(
            request = {
                chatApi.sendMessage(
                    SendMessageRequest(
                        message = SendMessageDataRequest(
                            data = "{}",
                            dialogId = dialog,
                            mediaUrl = null,
                            messageType = "TEXT",
                            text = text)
                    )
                )
            },
            onSuccess = { onSuccess(it?.id) },
            onFailure = onFailure
        )
    }
}
