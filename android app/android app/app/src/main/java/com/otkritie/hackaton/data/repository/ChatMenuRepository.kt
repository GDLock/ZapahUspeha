package com.otkritie.hackaton.data.repository

import com.otkritie.hackaton.data.remote.datasource.ChatMenuRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatMenuRepository @Inject constructor(
    private val chatMenuRemoteDataSource: ChatMenuRemoteDataSource
) {

    suspend fun getDialogId(
        onSuccess: (Int) -> Unit,
        onFailure: (String) -> Unit
    ) {
        chatMenuRemoteDataSource.getChatId(
            onSuccess = { onSuccess(it!!) },
            onFailure = onFailure
        )
    }
}
