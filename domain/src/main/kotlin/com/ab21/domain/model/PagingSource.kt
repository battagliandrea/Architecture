package com.ab21.domain.model

data class PagingSource<out T>(
    val count: Long,
    val hasNext: Boolean,
    val data: List<T>
)