package com.otkritie.hackaton.data.remote.websocket

import com.otkritie.hackaton.data.manager.AuthManager
import com.otkritie.hackaton.data.remote.interceptor.AuthInterceptor
import com.otkritie.hackaton.data.remote.interceptor.RestParamsInterceptor
import com.otkritie.hackaton.di.AppModule
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.logging.HttpLoggingInterceptor

class WebSocketClient(
    private val json: Json,
    private val authManager: AuthManager,
    private val socketUrl: String,
) {
    private var webSocket: WebSocket? = null
    private var shouldReconnect = false
    private var listener: WebSocketListener? = null

    private var client: OkHttpClient? = null

    private fun initWebSocket() {
//        Log.e("socketCheck", "initWebSocket() socketurl = $socketUrl")
        val request = Request.Builder().url(url = socketUrl).build()
        listener?.let {
            webSocket = client?.newWebSocket(request, it)
        }
        client?.dispatcher?.executorService?.shutdown()
    }

    fun connect(listener: WebSocketListener) {
        client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authManager))
            .addInterceptor(RestParamsInterceptor(json))
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
        shouldReconnect = true
        this.listener = listener
        initWebSocket()
    }

    fun reconnect() {
        initWebSocket()
    }

    fun disconnect() {
        shouldReconnect = false
        webSocket?.cancel()
        webSocket = null
        client = null
    }
}
