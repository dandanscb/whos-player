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


    private val mutableViewState: MutableLiveData<WhosPlayerViewState> = MutableLiveData()
    val viewState: LiveData<WhosPlayerViewState> =
        mutableViewState

    fun getSoccerPlayer(level: Int) {
        viewModelScope.launch {
            handleGetSoccerState(useCase.getSoccerPlayer(level))
        }
    }

    private fun handleGetSoccerState(result: WhosPlayerUseCaseState) {
        when (result) {
            is WhosPlayerUseCaseState.GetSoccerPlayer -> {
                mutableViewState.value = WhosPlayerViewState.GetSoccerPlayer(result.soccerPlayer)
            }

            is WhosPlayerUseCaseState.Error -> {
                // TODO
            }
        }
    }
}
