package br.com.whosplayer.app.whosplayer.usecase

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel

sealed class WhosPlayerUseCaseState {

    sealed class GetSoccerPlayerUseCaseState {
        data class GetSoccerPlayer(val soccerPlayer: SoccerPlayerModel) : GetSoccerPlayerUseCaseState()
        object NotFound : GetSoccerPlayerUseCaseState()
        object Error : GetSoccerPlayerUseCaseState()
    }

    sealed class GetPlayerLevelUseCaseState {
        data class GetPlayerLevel(val level: Long) : GetPlayerLevelUseCaseState()
        object EmptyState : GetPlayerLevelUseCaseState()
        object Error : GetPlayerLevelUseCaseState()
    }

    sealed class SavePlayerLevelUseCaseState {
        object Success : SavePlayerLevelUseCaseState()
        object Error : SavePlayerLevelUseCaseState()
    }
}
