package br.com.whosplayer.app.whosplayer.usecase

interface WhosPlayerUseCase {

    suspend fun getSoccerPlayer(level: Int): WhosPlayerUseCaseState
}
