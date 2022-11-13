package com.otkritie.hackaton.data.remote.interceptor

import com.otkritie.hackaton.data.manager.AuthManager
import okhttp3.Interceptor
import okhttp3.Request

class AuthInterceptor(
    private val authManager: AuthManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(newRequest(chain.request()))

    private fun newRequest(request: Request): Request {
        val accessToken = authManager.token ?: return request
        return request.newBuilder()
            .addHeader(HEADER_NAME_AUTHORIZATION, "$HEADER_VALUE_AUTHORIZATION $accessToken")
            .build()
    }

    private companion object {
        const val HEADER_NAME_AUTHORIZATION = "Authorization"
        const val HEADER_VALUE_AUTHORIZATION = "Bearer"
    }
}
