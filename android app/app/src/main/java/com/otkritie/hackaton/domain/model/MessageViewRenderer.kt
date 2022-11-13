package com.otkritie.hackaton.domain.model

data class MessageViewRenderer(
    val id: String,
    val text: String,
    val time: String,
    val mediaUrl: String?,
    val isMine: Boolean
)

//diff util callback