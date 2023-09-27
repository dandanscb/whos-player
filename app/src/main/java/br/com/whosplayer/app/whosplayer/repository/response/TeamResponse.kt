package br.com.whosplayer.app.whosplayer.repository.response

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val name: String,
    val crest: String,
    val year: String,
    var lastTeam: Boolean = false
)
