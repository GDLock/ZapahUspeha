package com.otkritie.hackaton.data.repository

import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.remote.datasource.AuthRemoteDataSource
import com.otkritie.hackaton.data.remote.model.Role
import com.otkritie.hackaton.data.remote.model.auth.AuthorizationResponse
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authPreference: AuthPreference,
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun authorize(
        login: String,
        password: String,
        onSuccess: (AuthorizationResponse?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val digest = MessageDigest.getInstance("SHA-256")
        digest.update(password.toByteArray())
        val byteData = digest.digest()
        val stringBuilder = StringBuilder()
        for (x in byteData) {
            val str = Integer.toHexString(java.lang.Byte.toUnsignedInt(x))
            if (str.length < 2) {
                stringBuilder.append('0')
            }
            stringBuilder.append(str)
        }
        val passwordHash = stringBuilder.toString()
        authRemoteDataSource.authorize(
            login = login,
            password = passwordHash,
            onSuccess = {
                authPreference.token = it?.token
                authPreference.role = it?.role ?: Role.CLIENT
                authPreference.id = it?.id ?: 0
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }
}
