package br.com.whosplayer.app.whosplayer.usecase

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel

sealed class WhosPlayerUseCaseState {

    data class GetSoccerPlayer(val soccerPlayer: SoccerPlayerModel) : WhosPlayerUseCaseState()
    object Error : WhosPlayerUseCaseState()
}
