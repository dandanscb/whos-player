package br.com.whosplayer.app.whosplayer.viewmodel

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel

sealed class WhosPlayerViewState {
    data class GetSoccerPlayer(val soccerPlayer: SoccerPlayerModel) : WhosPlayerViewState()
    object GenericError : WhosPlayerViewState()
}
