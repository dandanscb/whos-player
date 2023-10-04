package br.com.whosplayer.app.whosplayer.repository

sealed class WhosPlayerRepositoryState {

    sealed class SoccerPlayerRepositoryState {
        data class GetSoccerPlayer(val soccerPlayerModel: MutableMap<String, Any>?) :
            SoccerPlayerRepositoryState()

        object NotFound : SoccerPlayerRepositoryState()
        object Exception : SoccerPlayerRepositoryState()
    }

    sealed class GetPlayerLevelRepositoryState {
        data class GetPlayerLevel(val level: Long) : GetPlayerLevelRepositoryState()
        object NotFoundPlayer : GetPlayerLevelRepositoryState()
        object Error : GetPlayerLevelRepositoryState()
    }

    sealed class SavePlayerLevelRepositoryState {
        object Success : SavePlayerLevelRepositoryState()
        object Error : SavePlayerLevelRepositoryState()
    }
}
