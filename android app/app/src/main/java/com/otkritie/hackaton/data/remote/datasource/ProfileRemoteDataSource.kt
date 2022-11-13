package com.otkritie.hackaton.data.remote.datasource

import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.datasource.delegate.RemoteDataSourceDelegate
import com.otkritie.hackaton.data.remote.model.user_info.UserInfoRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRemoteDataSource @Inject constructor(
    private val chatApi: ChatApi,
    private val delegate: RemoteDataSourceDelegate
) {
    suspend fun changeUserInfo(
        avatar: String,
        name: String,
        surname: String,
        middleName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        delegate.sendRequest(
            request = { chatApi.changeUserInfo(UserInfoRequest(avatar, middleName, name, surname)) },
            onSuccess = { onSuccess() },
            onFailure = onFailure
        )
    }
}
