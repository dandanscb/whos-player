package br.com.whosplayer.app.onboarding.viewmodel

sealed interface OnboardingViewState {

    object ShowLoading : OnboardingViewState
    data class Success(val level: Int) : OnboardingViewState
    object FirstAccess : OnboardingViewState
    object GenericError : OnboardingViewState
}
