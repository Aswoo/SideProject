package com.example.sdutest.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeakerResponse(
    @SerialName("name")
    val name: String,
    @SerialName("introduction")
    val introduction: String,
    @SerialName("imageUrl")
    val imageUrl: String,
)
