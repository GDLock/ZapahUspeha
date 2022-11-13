package com.otkritie.hackaton.data.remote.serializer

import com.otkritie.hackaton.data.remote.model.Role
import com.otkritie.hackaton.data.remote.model.Role.CLIENT
import com.otkritie.hackaton.data.remote.model.Role.OPERATOR
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object RoleAsStringSerializer : KSerializer<Role> {
    override val descriptor = PrimitiveSerialDescriptor("Role", PrimitiveKind.STRING)
    private const val CLIENT_VALUE = "CLIENT"
    private const val OPERATOR_VALUE = "OPERATOR"

    override fun deserialize(decoder: Decoder): Role {
        return when (decoder.decodeString()) {
            CLIENT_VALUE -> CLIENT
            else -> OPERATOR
        }
    }

    override fun serialize(encoder: Encoder, value: Role) {
        val status = when (value) {
            CLIENT -> CLIENT_VALUE
            OPERATOR -> OPERATOR_VALUE
        }
        encoder.encodeString(status)
    }
}
