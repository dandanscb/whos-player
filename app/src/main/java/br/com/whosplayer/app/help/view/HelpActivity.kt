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
        configureCardViewDates()
        configureLetterByLetters()
        configureTips()
        configureLetterByFilledLetters()

        binding.closeButton.setOnClickListener {
            finish()
        }
        binding.continueButton.setOnClickListener {
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

    private fun configureCardViewDates() {
        binding.helpCardViewDates.firstTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_santos
            )
        )
        binding.helpCardViewDates.firstTeam.yearsPlayed.text =
            this.getString(R.string.whos_player_help_neymar_years_played_santos)


        binding.helpCardViewDates.secondTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_barcelona
            )
        )
        binding.helpCardViewDates.secondTeam.yearsPlayed.text =
            this.getString(R.string.whos_player_help_neymar_years_played_barcelona)

        binding.helpCardViewDates.thirdTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_paris_saint_germain
            )
        )
        binding.helpCardViewDates.thirdTeam.yearsPlayed.text =
            this.getString(R.string.whos_player_help_neymar_years_played_psg)

        binding.helpCardViewDates.fourthTeam.crestTeam.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.img_al_hilal
            )
        )
        binding.helpCardViewDates.fourthTeam.yearsPlayed.text =
            this.getString(R.string.whos_player_help_neymar_years_played_al_hilal)

        binding.helpCardViewDates.fourthTeam.arrowRight.visibility = View.INVISIBLE
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
        binding.firstTip.tipsTitle.text = this.getString(R.string.whos_player_help_first_tip_label)
        binding.firstTip.tipsValue.text =
            this.getString(R.string.whos_player_help_neymar_first_tip)
        binding.secondTip.tipsTitle.text = this.getString(R.string.whos_player_help_second_tip_label)
        binding.secondTip.tipsValue.text =
            this.getString(R.string.whos_player_help_neymar_second_tip)
        binding.thirdTip.tipsTitle.text = this.getString(R.string.whos_player_help_third_tip_label)
        binding.thirdTip.tipsValue.text =
            this.getString(R.string.whos_player_help_neymar_third_tip)
    }

    private fun configureLetterByFilledLetters() {
        binding.helpLetterByFilledLetter.firstLetter.letterEditText.setText("N")
        binding.helpLetterByFilledLetter.firstLetter.letterEditText.isEnabled = false
        binding.helpLetterByFilledLetter.secondLetter.letterEditText.setText("E")
        binding.helpLetterByFilledLetter.secondLetter.letterEditText.isEnabled = false
        binding.helpLetterByFilledLetter.thirdLetter.letterEditText.setText("Y")
        binding.helpLetterByFilledLetter.thirdLetter.letterEditText.isEnabled = false
        binding.helpLetterByFilledLetter.fourthLetter.letterEditText.setText("M")
        binding.helpLetterByFilledLetter.fourthLetter.letterEditText.isEnabled = false
        binding.helpLetterByFilledLetter.fifthLetter.letterEditText.setText("A")
        binding.helpLetterByFilledLetter.fifthLetter.letterEditText.isEnabled = false
        binding.helpLetterByFilledLetter.sixthLetter.letterEditText.setText("R")
        binding.helpLetterByFilledLetter.sixthLetter.letterEditText.isEnabled = false
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, HelpActivity::class.java)
    }
}
