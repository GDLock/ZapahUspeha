package com.otkritie.hackaton.data.remote.model.auth

import com.otkritie.hackaton.data.remote.model.Role
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthorizationResponse private constructor(

    @SerialName("userId")
    val id: Int,

    @SerialName("login")
    val login: String,

    @Contextual
    @SerialName("role")
    val role: Role,

    @SerialName("jwtToken")
    val token: String
)
