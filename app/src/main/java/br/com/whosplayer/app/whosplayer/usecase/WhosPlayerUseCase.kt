package br.com.whosplayer.app.whosplayer.usecase

interface WhosPlayerUseCase {

    suspend fun getSoccerPlayer(level: Int, numberOfColumns: Int): WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState
    suspend fun getPlayerLevel(androidId: String): WhosPlayerUseCaseState.GetPlayerLevelUseCaseState
    suspend fun savePlayerLevel(
        androidId: String,
        level: Int
    ): WhosPlayerUseCaseState.SavePlayerLevelUseCaseState
}
