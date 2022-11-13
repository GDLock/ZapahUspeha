package com.otkritie.hackaton.domain.mapper

import com.otkritie.hackaton.data.remote.model.messages.MessageData
import com.otkritie.hackaton.domain.model.MessageViewRenderer
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun MessageData.toViewRenderer(myId: Int) = MessageViewRenderer(
    id = id,
    text = text,
    time = tranformData(timestamp),
    mediaUrl = mediaUrl,
    isMine = myId == sender
)

private fun tranformData(timeStamp: Long): String {
    val instant = Instant.ofEpochMilli(timeStamp)
    val targetFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return targetFormatter.withZone(ZoneId.systemDefault()).format(instant)
}
