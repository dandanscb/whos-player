package br.com.whosplayer.app.whosplayer.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class TipsModel(
    val position: String,
    val nationality: String,
    val dateOfBirth: String
)
