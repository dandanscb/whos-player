package br.com.whosplayer.app.whosplayer.usecase

import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepository
import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepositoryState
import br.com.whosplayer.app.whosplayer.repository.mapper.WhosPlayerMapper
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse
import br.com.whosplayer.app.whosplayer.repository.response.StageResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WhosPlayerUseCaseImpl(
    private val repository: WhosPlayerRepository,
    private val mapper: WhosPlayerMapper
) : WhosPlayerUseCase {

    override suspend fun getSoccerPlayer(level: Int) =
        try {
            handleRepositoryState(level, repository.getSoccerPlayer(level))
        } catch (e: Exception) {
            WhosPlayerUseCaseState.Error
        }

    private fun handleRepositoryState(level: Int, result: WhosPlayerRepositoryState) =
        when (result) {
            is WhosPlayerRepositoryState.GetSoccerPlayer -> {
                handleDatabaseResponse(level, result.soccerPlayerModel)
            }

            else -> {
                WhosPlayerUseCaseState.Error
            }
        }

    private fun handleDatabaseResponse(level: Int, data: Map<String, Any>?) =
        data?.let {
            val firestoreDataJson = it["stages"]

            val stages = Json.decodeFromString<List<StageResponse>>(firestoreDataJson as String)

            var soccerPlayerResponse: SoccerPlayerResponse? = null
            for (stage in stages) {
                if (stage.level == level) {
                    soccerPlayerResponse = stage.soccerPlayer
                    break
                }
            }

            soccerPlayerResponse?.let { response ->
                WhosPlayerUseCaseState.GetSoccerPlayer(
                    mapper.convertSoccerPlayerResponseToModel(response)
                )
            } ?: run {
                WhosPlayerUseCaseState.Error
            }
        } ?: run {
            WhosPlayerUseCaseState.Error
        }
}
