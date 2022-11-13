package com.otkritie.hackaton.data.remote.datasource

import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.datasource.delegate.RemoteDataSourceDelegate
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationRequest
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val chatApi: ChatApi,
    private val delegate: RemoteDataSourceDelegate
) {
    suspend fun authorize(
        login: String,
        password: String,
        onSuccess: (AuthorizationResponse?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        delegate.sendRequest(
            request = { chatApi.authorization(AuthorizationRequest(login, password)) },
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}
