package br.com.whosplayer.app.whosplayer.repository

import br.com.whosplayer.app.whosplayer.repository.model.StageModel

interface WhosPlayerRepository {

    fun getStages() : List<StageModel>
}
