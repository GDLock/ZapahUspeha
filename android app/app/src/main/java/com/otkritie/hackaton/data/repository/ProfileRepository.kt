package com.otkritie.hackaton.data.repository

import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.remote.datasource.ProfileRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val authPreference: AuthPreference,
    private val profileRemoteDataSource: ProfileRemoteDataSource
) {

    val name = authPreference.name

    val surname = authPreference.surname

    val middleName = authPreference.middleName

    suspend fun changeProfileRequest(
        avatar: String,
        name: String,
        surname: String,
        middleName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        profileRemoteDataSource.changeUserInfo(
            avatar = avatar,
            name = name,
            surname = surname,
            middleName = middleName,
            onSuccess = {
                authPreference.name = name
                authPreference.surname = surname
                authPreference.middleName = middleName
                onSuccess()
            },
            onFailure = onFailure
        )
    }
}
