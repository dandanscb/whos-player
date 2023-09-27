package br.com.whosplayer.app.whosplayer.repository.response

import kotlinx.serialization.Serializable

@Serializable
data class SoccerPlayerResponse(
    val name: String,
    val tips: TipsResponse,
    val teams: List<List<TeamResponse>>,
    val picture: String
)
