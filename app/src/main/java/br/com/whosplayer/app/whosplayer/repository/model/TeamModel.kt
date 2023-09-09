package br.com.whosplayer.app.whosplayer.repository.model

data class TeamModel(
    val name: String,
    val crest: String,
    val year: String,
    var lastTeam: Boolean = false,
    var yearVisibility: Boolean = false
)
