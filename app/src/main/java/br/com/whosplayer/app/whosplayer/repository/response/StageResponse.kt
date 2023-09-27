package br.com.whosplayer.app.whosplayer.repository.response

import kotlinx.serialization.Serializable

@Serializable
data class StageResponse(
    val level: Int,
    val soccerPlayer: SoccerPlayerResponse
)
