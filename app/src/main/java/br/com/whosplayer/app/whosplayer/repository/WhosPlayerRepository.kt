package br.com.whosplayer.app.whosplayer.repository

interface WhosPlayerRepository {

    suspend fun getSoccerPlayer(level: Int): WhosPlayerRepositoryState.SoccerPlayerRepositoryState
    suspend fun getPlayerLevel(androidId: String): WhosPlayerRepositoryState.GetPlayerLevelRepositoryState

    suspend fun savePlayerLevel(
        androidId: String,
        level: Int
    ): WhosPlayerRepositoryState.SavePlayerLevelRepositoryState
}
