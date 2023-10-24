package br.com.whosplayer.app.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.whosplayer.app.whosplayer.repository.WhosPlayerRepositoryImpl
import br.com.whosplayer.app.whosplayer.repository.mapper.WhosPlayerMapperImpl
import br.com.whosplayer.app.whosplayer.usecase.WhosPlayerUseCaseImpl
import com.google.firebase.firestore.FirebaseFirestore

class OnboardingViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingViewModel(
            useCase = WhosPlayerUseCaseImpl(
                repository = WhosPlayerRepositoryImpl(
                    FirebaseFirestore.getInstance()
                ),
                mapper = WhosPlayerMapperImpl()
            )
        ) as T
    }
}
