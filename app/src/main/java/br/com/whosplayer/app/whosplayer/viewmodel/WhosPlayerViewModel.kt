package br.com.whosplayer.app.whosplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseImpl
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseState
import kotlinx.coroutines.launch

class WhosPlayerViewModel(
    private val useCase: WhosPlayerUseCaseImpl
) : ViewModel() {


    private val mutableViewState: MutableLiveData<WhosPlayerViewState.WhosPlayerSoccerPlayerViewState> =
        MutableLiveData()
    val viewState: LiveData<WhosPlayerViewState.WhosPlayerSoccerPlayerViewState> =
        mutableViewState


    private val mutableSaveLevelViewState: MutableLiveData<WhosPlayerViewState.WhosPlayerSaveLevelViewState> =
        MutableLiveData()
    val saveLevelViewState: LiveData<WhosPlayerViewState.WhosPlayerSaveLevelViewState> =
        mutableSaveLevelViewState

    fun getSoccerPlayer(androidId: String) {
        mutableViewState.value = WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.ShowLoading
        viewModelScope.launch {
            val level = handleGetPlayerLevelState(useCase.getPlayerLevel(androidId))
            level?.let {
                handleGetSoccerPlayerState(it, useCase.getSoccerPlayer(it))
            } ?: run {
                mutableViewState.value =
                    WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.GenericError
            }
        }
    }

    private fun handleGetSoccerPlayerState(
        level: Int,
        result: WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState
    ) {
        when (result) {
            is WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.GetSoccerPlayer -> {
                mutableViewState.value =
                    WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.GetSoccerPlayer(
                        level,
                        result.soccerPlayer
                    )
                mutableViewState.value =
                    WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.HideLoading
            }

            is WhosPlayerUseCaseState.GetSoccerPlayerUseCaseState.Error -> {
                // TODO
            }
        }
    }

    private fun handleGetPlayerLevelState(result: WhosPlayerUseCaseState.GetPlayerLevelUseCaseState): Int? =
        when (result) {
            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.GetPlayerLevel -> {
                result.level.toInt()
            }

            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.EmptyState -> {
                FIRST_INDEX
            }

            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.Error -> {
                null
            }
        }

    fun saveLevel(androidId: String, level: Int) {
        mutableSaveLevelViewState.value =
            WhosPlayerViewState.WhosPlayerSaveLevelViewState.ShowLoading
        viewModelScope.launch {
            handleSavePlayerLevelState(useCase.savePlayerLevel(androidId, level))
        }
    }

    private fun handleSavePlayerLevelState(result: WhosPlayerUseCaseState.SavePlayerLevelUseCaseState) {
        when (result) {
            is WhosPlayerUseCaseState.SavePlayerLevelUseCaseState.Success -> {
                mutableSaveLevelViewState.value =
                    WhosPlayerViewState.WhosPlayerSaveLevelViewState.Success
                mutableSaveLevelViewState.value =
                    WhosPlayerViewState.WhosPlayerSaveLevelViewState.HideLoading
            }

            is WhosPlayerUseCaseState.SavePlayerLevelUseCaseState.Error -> {
                mutableSaveLevelViewState.value =
                    WhosPlayerViewState.WhosPlayerSaveLevelViewState.GenericError
            }
        }
    }

    companion object {
        private const val FIRST_INDEX = 1
    }
}
