package br.com.whosplayer.app.help.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.whosplayer.R
import br.com.whosplayer.commons.view.CustomTipsTextView
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
        configureLetterByLetters()
        configureTips()

        binding.closeButton.setOnClickListener {
            finish()
        }
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

    private fun configureLetterByLetters() {
        binding.helpLetterByLetter.firstLetter.letterEditText.visibility = View.GONE
        binding.helpLetterByLetter.secondLetter.letterEditText.visibility = View.GONE
        binding.helpLetterByLetter.thirdLetter.letterEditText.visibility = View.GONE
        binding.helpLetterByLetter.fourthLetter.letterEditText.visibility = View.GONE
        binding.helpLetterByLetter.fifthLetter.letterEditText.visibility = View.GONE
        binding.helpLetterByLetter.sixthLetter.letterEditText.visibility = View.GONE
    }

    private fun configureTips() {
        binding.firstTip.tipsTitle.text = "Dica 1"
        binding.firstTip.tipsValue.text = "A data de nascimento do Jogador é 5 de fevereiro de 1992."
        binding.secondTip.tipsTitle.text = "Dica 2"
        binding.secondTip.tipsValue.text = "O Jogador joga como Atacante."
        binding.thirdTip.tipsTitle.text = "Dica 3"
        binding.thirdTip.tipsValue.text = "Este jogador é da nacionalidade: Brasileiro."
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, HelpActivity::class.java)
    }
}
