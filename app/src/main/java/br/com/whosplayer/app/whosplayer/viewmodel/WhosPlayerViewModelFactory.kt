package br.com.whosplayer.app.whosplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepositoryImpl
import br.com.whosplayer.app.whosplayer.repository.mapper.WhosPlayerMapperImpl
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseImpl
import com.google.firebase.firestore.FirebaseFirestore

class WhosPlayerViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WhosPlayerViewModel(
            useCase = WhosPlayerUseCaseImpl(
                repository = WhosPlayerRepositoryImpl(
                    FirebaseFirestore.getInstance()
                ),
                mapper = WhosPlayerMapperImpl()
            )
        ) as T
    }
}
