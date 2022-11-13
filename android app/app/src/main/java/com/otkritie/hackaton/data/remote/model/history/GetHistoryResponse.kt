package com.otkritie.hackaton.data.remote.model.history

import com.otkritie.hackaton.data.remote.model.messages.MessageData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetHistoryResponse private constructor(

    @SerialName("messages")
    val messages: List<MessageData>
)
