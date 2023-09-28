package br.com.whosplayer.app.whosplayer.usecase

import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepository
import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepositoryState
import br.com.whosplayer.app.whosplayer.repository.mapper.WhosPlayerMapper
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WhosPlayerUseCaseImpl(
    private val repository: WhosPlayerRepository,
    private val mapper: WhosPlayerMapper
) : WhosPlayerUseCase {

    override suspend fun getSoccerPlayer(level: Int) =
        try {
            handleRepositoryState(repository.getSoccerPlayer(level))
        } catch (e: Exception) {
            WhosPlayerUseCaseState.Error
        }

    private fun handleRepositoryState(result: WhosPlayerRepositoryState) =
        when (result) {
            is WhosPlayerRepositoryState.GetSoccerPlayer -> {
                handleDatabaseResponse(result.soccerPlayerModel)
            }

            else -> {
                WhosPlayerUseCaseState.Error
            }
        }

    private fun handleDatabaseResponse(data: Map<String, Any>?) =
        data?.let {
            val firestoreDataJson = it["stages"]
            val soccerPlayerResponse =
                Json.decodeFromString<SoccerPlayerResponse>(firestoreDataJson as String)
            soccerPlayerResponse.let { response ->
                WhosPlayerUseCaseState.GetSoccerPlayer(
                    mapper.convertSoccerPlayerResponseToModel(response)
                )
            }
        } ?: run {
            WhosPlayerUseCaseState.Error
        }
}
