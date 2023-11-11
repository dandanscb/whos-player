package br.com.whosplayer.app.whosplayer.repository.mapper

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse

interface WhosPlayerMapper {

    fun convertSoccerPlayerResponseToModel(
        soccerPlayerResponse: SoccerPlayerResponse,
        numberOfColumns: Int
    ): SoccerPlayerModel
}