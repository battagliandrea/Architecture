package com.ab21.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class PagingSource<T>(
    val count: Long,
    val hasNext: Boolean,
    val data: List<T>
)