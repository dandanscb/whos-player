package br.com.whosplayer.app.whosplayer.repository

interface WhosPlayerRepository {

    suspend fun getSoccerPlayer(level: Int) : WhosPlayerRepositoryState
}
