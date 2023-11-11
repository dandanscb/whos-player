package br.com.whosplayer.app.onboarding.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.whosplayer.app.onboarding.view.OnboardingActivity
import br.com.whosplayer.databinding.FragmentWhosPlayerOnboardingTipsBinding

class OnboardingTipsFragment : Fragment() {

    private lateinit var binding: FragmentWhosPlayerOnboardingTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLocalInflater = inflater.cloneInContext(requireContext())
        return setupBindingLayout(fragmentLocalInflater)
    }

    private fun setupBindingLayout(fragmentLocalInflater: LayoutInflater): View {
        return FragmentWhosPlayerOnboardingTipsBinding.inflate(
            fragmentLocalInflater
        ).root.also {
            binding = FragmentWhosPlayerOnboardingTipsBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.skipButton.setOnClickListener {
            val parentActivity = activity as OnboardingActivity
            parentActivity.goToNextFragment()
        }
    }
}
