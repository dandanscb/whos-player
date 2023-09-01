package br.com.whosplayer.app.whosplayer.repository

import br.com.whosplayer.app.whosplayer.repository.mock.WhosPlayerMock
import br.com.whosplayer.app.whosplayer.repository.model.StageModel

class WhosPlayerRepositoryImpl : WhosPlayerRepository {

    override fun getStages(): List<StageModel> {
        return WhosPlayerMock.getStageModelMock()
    }
}
