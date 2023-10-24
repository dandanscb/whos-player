package br.com.whosplayer.app.onboarding.view

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.whosplayer.app.onboarding.view.adapter.OnboardingViewPagerAdapter
import br.com.whosplayer.app.onboarding.viewmodel.OnboardingViewModel
import br.com.whosplayer.app.onboarding.viewmodel.OnboardingViewModelFactory
import br.com.whosplayer.app.onboarding.viewmodel.OnboardingViewState
import br.com.whosplayer.app.whosplayer.view.WhosPlayerActivity
import br.com.whosplayer.commons.database.getAndroidID
import br.com.whosplayer.commons.view.CustomSplashScreen
import br.com.whosplayer.databinding.ActivityWhosPlayerOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerOnboardingBinding
    private var viewModel: OnboardingViewModel? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initViewModel()
        initObservable()
    }

    private fun initViewModel() {
        val factory = OnboardingViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[OnboardingViewModel::class.java]

        viewModel?.getPlayerLevel(getAndroidID(this))
    }

    private fun init() {
        binding = ActivityWhosPlayerOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = OnboardingViewPagerAdapter(supportFragmentManager)
    }
    
    private fun initObservable() {
        viewModel?.viewState?.observe(this) {
            when (it) {
                is OnboardingViewState.Success -> {
                    val intent = WhosPlayerActivity.newInstance(this, it.level)
                    startActivity(intent)
                    finish()
                }

                is OnboardingViewState.FirstAccess -> {
                    binding.frameLayout.visibility = View.GONE
                    binding.viewPager.visibility = View.VISIBLE
                }

                is OnboardingViewState.ShowLoading -> {
                    val splashScreen = CustomSplashScreen(this)
                    val layoutParams = ViewGroup.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT
                    )
                    binding.frameLayout.addView(splashScreen, layoutParams)
                    binding.viewPager.visibility = View.GONE
                    binding.frameLayout.visibility = View.VISIBLE
                }

                is OnboardingViewState.GenericError -> {
                    binding.errorScreen.visibility = View.VISIBLE
                    binding.errorScreen.closeClickListener {
                        finish()
                    }
                    binding.frameLayout.visibility = View.GONE
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, OnboardingActivity::class.java)
    }
}
