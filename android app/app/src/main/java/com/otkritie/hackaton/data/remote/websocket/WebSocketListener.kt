package com.otkritie.hackaton.data.remote.websocket

import android.util.Log
import com.otkritie.hackaton.data.remote.model.messages.GetMessageResponse
import com.otkritie.hackaton.data.remote.model.messages.MessageData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONObject

class WebSocketListener(
    private val onMessageReceived: (MessageData) -> Unit
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val jsonObject = Json.decodeFromString<GetMessageResponse>(text)
        onMessageReceived(jsonObject.messageData)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.e("CLOSING", reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.e("CLOSE", reason)
    }
}
