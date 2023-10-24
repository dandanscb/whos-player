package br.com.whosplayer.app.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseImpl
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseState
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val useCase: WhosPlayerUseCaseImpl
) : ViewModel() {


    private val mutableViewState: MutableLiveData<OnboardingViewState> =
        MutableLiveData()
    val viewState: LiveData<OnboardingViewState> =
        mutableViewState

    fun getPlayerLevel(androidId: String) {
        mutableViewState.value = OnboardingViewState.ShowLoading
        viewModelScope.launch {
            handleGetPlayerLevelState(useCase.getPlayerLevel(androidId))
        }
    }

    private fun handleGetPlayerLevelState(result: WhosPlayerUseCaseState.GetPlayerLevelUseCaseState) {
        when (result) {
            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.GetPlayerLevel -> {
                mutableViewState.value = OnboardingViewState.Success(result.level.toInt())
            }

            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.EmptyState -> {
                mutableViewState.value = OnboardingViewState.FirstAccess
            }

            is WhosPlayerUseCaseState.GetPlayerLevelUseCaseState.Error -> {
                mutableViewState.value = OnboardingViewState.GenericError
            }
        }
    }


    companion object {
        private const val FIRST_INDEX = 1
    }
}
