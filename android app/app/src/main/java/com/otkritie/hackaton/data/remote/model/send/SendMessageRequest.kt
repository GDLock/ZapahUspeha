package com.otkritie.hackaton.data.remote.model.send


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SendMessageRequest(

    @SerialName("message")
    val message: SendMessageDataRequest
)

@Serializable
class SendMessageDataRequest(

    @SerialName("data")
    val data: String,

    @SerialName("dialogId")
    val dialogId: Int,

    @SerialName("mediaUrl")
    val mediaUrl: String?,

    @SerialName("messageType")
    val messageType: String,

    @SerialName("text")
    val text: String
)
