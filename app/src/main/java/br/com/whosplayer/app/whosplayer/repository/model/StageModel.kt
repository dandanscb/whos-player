package br.com.whosplayer.app.whosplayer.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class StageModel(
    val level: Int,
    val soccerPlayer: SoccerPlayerModel
)
