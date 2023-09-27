package br.com.whosplayer.app.whosplayer.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class SoccerPlayerModel(
    val name: String,
    val nameLetterByLetter: List<List<Char>>,
    val tips: TipsModel,
    val teams: List<List<TeamModel>>,
    val picture: String
)
