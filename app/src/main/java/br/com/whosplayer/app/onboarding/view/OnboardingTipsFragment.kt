package br.com.whosplayer.app.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import br.com.whosplayer.R
import br.com.whosplayer.app.whosplayer.view.WhosPlayerActivity

class OnboardingTipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_whos_player_onboarding_tips, container, false)

        val transparentButton = rootView.findViewById<Button>(R.id.skip_button)

        transparentButton.setOnClickListener {
            val intent = WhosPlayerActivity.newInstance(requireContext(),
                FIRST_INDEX
            )
            startActivity(intent)
            requireActivity().finish()
        }

        return rootView
    }

    companion object {
        private const val FIRST_INDEX = 1
    }
}
