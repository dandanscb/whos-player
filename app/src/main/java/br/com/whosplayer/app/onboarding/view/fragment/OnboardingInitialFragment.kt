package br.com.whosplayer.app.onboarding.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.whosplayer.R
import br.com.whosplayer.app.onboarding.view.OnboardingActivity
import br.com.whosplayer.databinding.FragmentWhosPlayerOnboardingInitialBinding

class OnboardingInitialFragment : Fragment() {

    private lateinit var binding: FragmentWhosPlayerOnboardingInitialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLocalInflater = inflater.cloneInContext(requireContext())
        return setupBindingLayout(fragmentLocalInflater)
    }

    private fun setupBindingLayout(fragmentLocalInflater: LayoutInflater): View {
        return FragmentWhosPlayerOnboardingInitialBinding.inflate(
            fragmentLocalInflater
        ).root.also {
            binding = FragmentWhosPlayerOnboardingInitialBinding.bind(it)
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

        configureCardView()
    }

    private fun configureCardView() {
        binding.initialCardView.firstTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.img_santos
            )
        )

        binding.initialCardView.secondTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.img_barcelona
            )
        )

        binding.initialCardView.thirdTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.img_paris_saint_germain
            )
        )

        binding.initialCardView.fourthTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.img_al_hilal
            )
        )
        binding.initialCardView.fourthTeam.arrowRight.visibility = View.INVISIBLE
    }
}
