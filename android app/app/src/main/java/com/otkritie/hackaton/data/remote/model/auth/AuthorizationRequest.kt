package com.otkritie.hackaton.data.remote.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthorizationRequest(

    @SerialName("login")
    val login: String,

    @SerialName("password")
    val password: String
)
