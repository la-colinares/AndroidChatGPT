package com.lacolinares.androidchatgpt.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@ExperimentalSerializationApi
@Serializer(forClass = MessageType::class)
object MessageTypeSerializer: KSerializer<MessageType>{

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("messageType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MessageType {
        val value = decoder.decodeString()
        return MessageType.values().find { it.value == value } ?: throw SerializationException("$value enum is not yer added.")
    }

    override fun serialize(encoder: Encoder, value: MessageType) {
        encoder.encodeString(value.value)
    }

}