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
            handleGetSoccerPlayerRepositoryState(repository.getSoccerPlayer(level))
        } catch (e: Exception) {
            WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.Error
        }

    override suspend fun getPlayerLevel(androidId: String) = try {
        handleGetPlayerLevelRepositoryState(repository.getPlayerLevel(androidId))
    } catch (e: Exception) {
        WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.Error
    }

    override suspend fun savePlayerLevel(
        androidId: String,
        level: Int
    ) = try {
        handleSavePlayerLevelRepositoryState(repository.savePlayerLevel(androidId, level))
    } catch (e: Exception) {
        WhosPlayerUseCaseState.SavePlayerLevelUseCaseState.Error
    }

    private fun handleGetSoccerPlayerRepositoryState(result: WhosPlayerRepositoryState.SoccerPlayerRepositoryState) =
        when (result) {
            is WhosPlayerRepositoryState.SoccerPlayerRepositoryState.GetSoccerPlayer -> {
                handleGetSoccerPlayerDatabaseResponse(result.soccerPlayerModel)
            }

            is WhosPlayerRepositoryState.SoccerPlayerRepositoryState.NotFound -> {
                WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.NotFound
            }

            else -> {
                WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.Error
            }
        }

    private fun handleGetSoccerPlayerDatabaseResponse(data: Map<String, Any>?) =
        data?.let {
            val firestoreDataJson = it["stages"]
            val soccerPlayerResponse =
                Json.decodeFromString<SoccerPlayerResponse>(firestoreDataJson as String)
            soccerPlayerResponse.let { response ->
                WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.GetSoccerPlayer(
                    mapper.convertSoccerPlayerResponseToModel(response)
                )
            }
        } ?: run {
            WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.Error
        }

    private fun handleGetPlayerLevelRepositoryState(result: WhosPlayerRepositoryState.GetPlayerLevelRepositoryState) =
        when (result) {
            is WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.GetPlayerLevel -> {
                WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.GetPlayerLevel(result.level)
            }

            is WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.NotFoundPlayer -> {
                WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.EmptyState
            }

            else -> {
                WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.Error
            }
        }

    private fun handleSavePlayerLevelRepositoryState(result: WhosPlayerRepositoryState.SavePlayerLevelRepositoryState) =
        when (result) {
            is WhosPlayerRepositoryState.SavePlayerLevelRepositoryState.Success -> {
                WhosPlayerUseCaseState.SavePlayerLevelUseCaseState.Success
            }

            else -> {
                WhosPlayerUseCaseState.SavePlayerLevelUseCaseState.Error
            }
        }
}
