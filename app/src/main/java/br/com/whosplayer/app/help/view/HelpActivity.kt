package br.com.whosplayer.app.help.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.whosplayer.R
import br.com.whosplayer.databinding.ActivityWhosPlayerHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureCardView()
    }

    private fun configureCardView() {
        binding.helpCardView.firstTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_santos
            )
        )

        binding.helpCardView.secondTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_barcelona
            )
        )

        binding.helpCardView.thirdTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_paris_saint_germain
            )
        )

        binding.helpCardView.fourthTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_al_hilal
            )
        )
        binding.helpCardView.fourthTeam.arrowRight.visibility = View.INVISIBLE
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, HelpActivity::class.java)
    }
}
