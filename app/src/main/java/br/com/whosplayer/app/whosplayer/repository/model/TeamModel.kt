package br.com.whosplayer.app.whosplayer.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class TeamModel(
    val name: String,
    val crest: String,
    val year: String,
    var lastTeam: Boolean = false
)
