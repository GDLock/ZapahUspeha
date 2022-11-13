package com.otkritie.hackaton.data.remote.model.send


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("data")
    val `data`: String,
    @SerialName("dialogId")
    val dialogId: Int,
    @SerialName("mediaUrl")
    val mediaUrl: String,
    @SerialName("messageType")
    val messageType: String,
    @SerialName("text")
    val text: String
)
