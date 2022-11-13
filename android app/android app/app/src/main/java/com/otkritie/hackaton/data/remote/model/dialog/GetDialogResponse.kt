package com.otkritie.hackaton.data.remote.model.dialog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetDialogResponse(

    @SerialName("dialogId")
    val id: Int
)
