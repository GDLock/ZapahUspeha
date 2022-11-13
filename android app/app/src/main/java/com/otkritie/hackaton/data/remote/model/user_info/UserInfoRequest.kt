package com.otkritie.hackaton.data.remote.model.user_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserInfoRequest(

    @SerialName("avatar")
    val avatar: String,

    @SerialName("middleName")
    val middleName: String,

    @SerialName("name")
    val name: String,

    @SerialName("surname")
    val surname: String
)
