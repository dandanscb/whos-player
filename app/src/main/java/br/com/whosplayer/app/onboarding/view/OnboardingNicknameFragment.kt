package br.com.whosplayer.app.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.whosplayer.databinding.FragmentWhosPlayerOnboardingNicknameBinding

class OnboardingNicknameFragment : Fragment() {

    private lateinit var binding: FragmentWhosPlayerOnboardingNicknameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLocalInflater = inflater.cloneInContext(requireContext())
        return setupBindingLayout(fragmentLocalInflater)
    }

    private fun setupBindingLayout(fragmentLocalInflater: LayoutInflater): View {
        return FragmentWhosPlayerOnboardingNicknameBinding.inflate(
            fragmentLocalInflater
        ).root.also {
            binding = FragmentWhosPlayerOnboardingNicknameBinding.bind(it)
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
        configureLetterByLetters()
    }

    private fun configureLetterByLetters() {
        binding.nicknameLetterByLetter.firstLetter.letterEditText.visibility = View.GONE
        binding.nicknameLetterByLetter.secondLetter.letterEditText.visibility = View.GONE
        binding.nicknameLetterByLetter.thirdLetter.letterEditText.visibility = View.GONE
        binding.nicknameLetterByLetter.fourthLetter.letterEditText.visibility = View.GONE
        binding.nicknameLetterByLetter.fifthLetter.letterEditText.visibility = View.GONE
        binding.nicknameLetterByLetter.sixthLetter.letterEditText.visibility = View.GONE
    }
}