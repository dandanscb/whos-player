package br.com.whosplayer.app.whosplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepository
import br.com.whosplayer.app.whosplayer.repository.model.StageModel

class WhosPlayerViewModel(
    val repository: WhosPlayerRepository
) : ViewModel() {

    private val _stages = MutableLiveData<List<StageModel>>()
    val stage: LiveData<List<StageModel>>
        get() = _stages

    fun getStages() {
        _stages.value = repository.getStages()
    }
}
