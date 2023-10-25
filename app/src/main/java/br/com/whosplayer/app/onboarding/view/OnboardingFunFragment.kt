package br.com.whosplayer.app.onboarding.view

import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.whosplayer.app.whosplayer.view.WhosPlayerActivity
import br.com.whosplayer.databinding.FragmentWhosPlayerOnboardingFunBinding

class OnboardingFunFragment : Fragment() {

    private lateinit var binding: FragmentWhosPlayerOnboardingFunBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLocalInflater = inflater.cloneInContext(requireContext())
        return setupBindingLayout(fragmentLocalInflater)
    }

    private fun setupBindingLayout(fragmentLocalInflater: LayoutInflater): View {
        return FragmentWhosPlayerOnboardingFunBinding.inflate(
            fragmentLocalInflater
        ).root.also {
            binding = FragmentWhosPlayerOnboardingFunBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.closeButton.setOnClickListener {
            val intent = WhosPlayerActivity.newInstance(requireContext(),
                FIRST_INDEX
            )
            startActivity(intent)
            requireActivity().finish()
        }

        val spannableString = SpannableString(binding.funDescription.text)
        spannableString.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL),
            START_INDEX,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.funDescription.text = spannableString
    }

    companion object {
        private const val FIRST_INDEX = 1
        private const val START_INDEX = 0
    }
}
