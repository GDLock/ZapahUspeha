package com.otkritie.hackaton.data.remote.datasource

import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.datasource.delegate.RemoteDataSourceDelegate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatMenuRemoteDataSource @Inject constructor(
    private val chatApi: ChatApi,
    private val delegate: RemoteDataSourceDelegate
) {

    suspend fun getChatId(
        onSuccess: (Int?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        delegate.sendRequest(
            request = { chatApi.getDialogId() },
            onSuccess = { onSuccess(it?.id) },
            onFailure = onFailure
        )
    }
}
