package br.com.whosplayer.app.whosplayer.viewmodel

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel

sealed class WhosPlayerViewState {

    sealed class WhosPlayerSoccerPlayerViewState {

        object ShowLoading : WhosPlayerSoccerPlayerViewState()
        data class GetSoccerPlayer(val level: Int, val soccerPlayer: SoccerPlayerModel) :
            WhosPlayerSoccerPlayerViewState()
        object GenericError : WhosPlayerSoccerPlayerViewState()
        object HideLoading : WhosPlayerSoccerPlayerViewState()
    }

    sealed class WhosPlayerSaveLevelViewState {

        object ShowLoading : WhosPlayerSaveLevelViewState()
        object Success : WhosPlayerSaveLevelViewState()
        object GenericError : WhosPlayerSaveLevelViewState()
        object HideLoading : WhosPlayerSaveLevelViewState()
    }

}
