package com.otkritie.hackaton.data.remote.model.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageData(

    @SerialName("data")
    val data: String? = null,

    @SerialName("dialogId")
    val dialogId: Int,

    @SerialName("mediaUrl")
    val mediaUrl: String? = null,

    @SerialName("messageId")
    val id: String,

    @SerialName("messageType")
    val type: String,

    @SerialName("recipient")
    val recipient: Int,

    @SerialName("sender")
    val sender: Int,

    @SerialName("text")
    val text: String,

    @SerialName("timestamp")
    val timestamp: Long
)
