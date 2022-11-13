package com.otkritie.hackaton.data.remote.model.messages


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetMessageResponse private constructor(

    @SerialName("messageData")
    val messageData: MessageData
)
