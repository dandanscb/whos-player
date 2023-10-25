package br.com.whosplayer.app.onboarding.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.whosplayer.app.onboarding.view.OnboardingFunFragment
import br.com.whosplayer.app.onboarding.view.OnboardingInitialFragment
import br.com.whosplayer.app.onboarding.view.OnboardingTipsFragment
import br.com.whosplayer.app.onboarding.view.OnboardingWelcomeFragment

class OnboardingViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = listOf(
        OnboardingWelcomeFragment(),
        OnboardingInitialFragment(),
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