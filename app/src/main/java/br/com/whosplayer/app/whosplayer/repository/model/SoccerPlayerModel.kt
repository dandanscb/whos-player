package br.com.whosplayer.app.whosplayer.repository.model

data class SoccerPlayerModel(
    val name: String,
    val tips: TipsModel,
    val teams: List<List<TeamModel>>,
    val picture: String
)
