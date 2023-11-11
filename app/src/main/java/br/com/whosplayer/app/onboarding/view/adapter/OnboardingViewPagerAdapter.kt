package br.com.whosplayer.app.onboarding.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.whosplayer.app.onboarding.view.fragment.OnboardingFunFragment
import br.com.whosplayer.app.onboarding.view.fragment.OnboardingInitialFragment
import br.com.whosplayer.app.onboarding.view.fragment.OnboardingNicknameFragment
import br.com.whosplayer.app.onboarding.view.fragment.OnboardingTipsFragment
import br.com.whosplayer.app.onboarding.view.fragment.OnboardingWelcomeFragment

class OnboardingViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = listOf(
        OnboardingWelcomeFragment(),
        OnboardingInitialFragment(),
        OnboardingNicknameFragment(),
        OnboardingTipsFragment(),
        OnboardingFunFragment()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}