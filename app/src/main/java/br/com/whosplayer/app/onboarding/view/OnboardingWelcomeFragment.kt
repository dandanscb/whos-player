package br.com.whosplayer.app.onboarding.view

import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.whosplayer.databinding.FragmentWhosPlayerOnboardingWelcomeBinding

class OnboardingWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWhosPlayerOnboardingWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLocalInflater = inflater.cloneInContext(requireContext())
        return setupBindingLayout(fragmentLocalInflater)
    }

    private fun setupBindingLayout(fragmentLocalInflater: LayoutInflater): View {
        return FragmentWhosPlayerOnboardingWelcomeBinding.inflate(
            fragmentLocalInflater
        ).root.also {
            binding = FragmentWhosPlayerOnboardingWelcomeBinding.bind(it)
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