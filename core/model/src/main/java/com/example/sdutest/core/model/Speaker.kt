package com.example.sdutest.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    val name: String,
    val introduction: String,
    val imageUrl: String,
)
