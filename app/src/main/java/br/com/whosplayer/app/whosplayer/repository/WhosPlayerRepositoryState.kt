package br.com.whosplayer.app.whosplayer.repository

sealed class WhosPlayerRepositoryState {
    data class GetSoccerPlayer(val soccerPlayerModel: MutableMap<String, Any>?) :
        WhosPlayerRepositoryState()

    object NotFound : WhosPlayerRepositoryState()
    object Exception : WhosPlayerRepositoryState()
}
