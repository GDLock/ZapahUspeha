package com.otkritie.hackaton.data.remote.model.send

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SendMessageResponse private constructor(

    @SerialName("messageId")
    val id: String
)
