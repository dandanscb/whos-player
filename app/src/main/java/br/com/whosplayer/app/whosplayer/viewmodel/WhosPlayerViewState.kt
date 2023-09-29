package br.com.whosplayer.app.whosplayer.viewmodel

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel

sealed class WhosPlayerViewState {

    object ShowLoading : WhosPlayerViewState()
    data class GetSoccerPlayer(val soccerPlayer: SoccerPlayerModel) : WhosPlayerViewState()
    object GenericError : WhosPlayerViewState()

    object HideLoading : WhosPlayerViewState()
}
