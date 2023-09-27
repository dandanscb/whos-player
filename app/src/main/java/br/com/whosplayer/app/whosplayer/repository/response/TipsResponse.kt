package br.com.whosplayer.app.whosplayer.repository.response

import kotlinx.serialization.Serializable

@Serializable
data class TipsResponse(
    val position: String,
    val nationality: String,
    val dateOfBirth: String
)
