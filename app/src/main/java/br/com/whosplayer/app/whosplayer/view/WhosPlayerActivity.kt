package br.com.whosplayer.app.whosplayer.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R
import br.com.whosplayer.app.help.view.HelpActivity
import br.com.whosplayer.app.whosplayer.view.adapter.NameLetterByLetterAdapter
import br.com.whosplayer.app.whosplayer.view.adapter.TeamCrestAdapter
import br.com.whosplayer.app.whosplayer.view.utils.NonScrollableGridLayoutManager
import br.com.whosplayer.databinding.ActivityWhosPlayerBinding
import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import br.com.whosplayer.app.whosplayer.repository.mock.WhosPlayerMock.getTipsMessages
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import br.com.whosplayer.app.whosplayer.repository.model.TipsModel
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewModel
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewModelFactory
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewState
import br.com.whosplayer.commons.view.CustomTipsTextView

class WhosPlayerActivity : AppCompatActivity(), NameLetterByLetterAdapter.EditTextFocusListener {

    private lateinit var binding: ActivityWhosPlayerBinding

    private var teamCrestAdapter = mutableListOf<TeamCrestAdapter>()

    private var nameLetterByLetterAdapter = mutableListOf<NameLetterByLetterAdapter>()
    private var recyclerViewReference = mutableListOf<RecyclerView>()

    private var viewModel: WhosPlayerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
        initObservable()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "QUAL JOGADOR ?"

        configDateButtons()
        configConfirmationButton()
    }

    private fun initViewModel() {
        val factory = WhosPlayerViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[WhosPlayerViewModel::class.java]

        viewModel?.getSoccerPlayer(soccerPlayerValue)
    }

    private fun initObservable() {
        viewModel?.viewState?.observe(this) {
            when (it) {
                is WhosPlayerViewState.GetSoccerPlayer -> {
                    displayCrests(it.soccerPlayer.teams)
                    showFieldForLetters(it.soccerPlayer.nameLetterByLetter)
                    configTipsButtons(it.soccerPlayer.tips)
                }

                is WhosPlayerViewState.GenericError -> {
                    // not used yet
                }
            }
        }
    }

    private fun displayCrests(teams: List<List<TeamModel>>) {
        teams.forEach {
            val recyclerView = RecyclerView(this)

            val layoutManager = NonScrollableGridLayoutManager(this, spanCount)
            val screenWidth = resources.displayMetrics.widthPixels
            val desiredColumnWidth = resources.getDimensionPixelSize(R.dimen.whos_player_80dp)
            val columns = screenWidth / desiredColumnWidth
            layoutManager.spanCount = columns.coerceAtMost(spanCount)

            recyclerView.layoutManager = layoutManager

            val adapter = TeamCrestAdapter(this, it)
            recyclerView.adapter = adapter

            binding.cardViewContainer.addView(recyclerView)
            recyclerView.layoutParams.width = RecyclerView.LayoutParams.WRAP_CONTENT
            teamCrestAdapter.add(adapter)
        }
    }

    private fun showFieldForLetters(nameLetterByLetter: List<List<Char>>) {
        var index = FIRST_INDEX
        nameLetterByLetter.forEach {
            val recyclerView = RecyclerView(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER

            val layoutManager = NonScrollableGridLayoutManager(this, it.size)
            recyclerView.layoutManager = layoutManager

            val adapter = NameLetterByLetterAdapter(index, it)
            adapter.editTextFocusListener = this
            recyclerView.adapter = adapter

            recyclerView.layoutParams = layoutParams

            binding.fieldLettersContainer.addView(recyclerView)
            recyclerView.layoutParams.width = RecyclerView.LayoutParams.WRAP_CONTENT
            recyclerViewReference.add(recyclerView)
            nameLetterByLetterAdapter.add(adapter)
            index++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_whos_player, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_help -> {
                val intent = HelpActivity.newInstance(this)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onLetterTyped(recyclerViewPosition: Int, position: Int) {
        if (position < nameLetterByLetterAdapter[recyclerViewPosition].itemCount) {
            if (position == NEGATIVE_NUMBER_ONE) {
                if (recyclerViewPosition - NUMBER_ONE >= FIRST_INDEX) {
                    val layoutManager =
                        recyclerViewReference[recyclerViewPosition - NUMBER_ONE].layoutManager as LinearLayoutManager
                    layoutManager.findViewByPosition(
                        recyclerViewReference[recyclerViewPosition - NUMBER_ONE].size - NUMBER_ONE
                    )
                        ?.findViewById<EditText>(R.id.letterEditText)?.requestFocus()
                }
            } else {
                val layoutManager =
                    recyclerViewReference[recyclerViewPosition].layoutManager as LinearLayoutManager
                layoutManager.findViewByPosition(position)
                    ?.findViewById<EditText>(R.id.letterEditText)?.requestFocus()
            }
        } else {
            if (recyclerViewPosition + NUMBER_ONE < recyclerViewReference.size) {
                val layoutManager =
                    recyclerViewReference[recyclerViewPosition + NUMBER_ONE].layoutManager as LinearLayoutManager
                layoutManager.findViewByPosition(FIRST_INDEX)
                    ?.findViewById<EditText>(R.id.letterEditText)
                    ?.requestFocus()
            }
        }
    }

    private fun configTipsButtons(tips: TipsModel) {
        val tipsMessage = getTipsMessages(tips)
        var remainingTips = tipsMessage.size

        binding.remainingHintNumbers.text = remainingTips.toString()

        binding.tipsButton.setOnClickListener {
            if (remainingTips != FIRST_INDEX) {
                showCustomDialog(remainingTips, tipsMessage)
                remainingTips--
                configAnimationNumberTips()
                binding.remainingHintNumbers.text = remainingTips.toString()
            } else {
                showCustomDialog(NUMBER_ONE, tipsMessage)
                binding.remainingHintNumbers.text = remainingTips.toString()
            }
        }

    }

    private fun configAnimationNumberTips() {
        val scaleXAnimator = ObjectAnimator.ofFloat(
            binding.tipsNumberAnimation,
            "scaleX",
            1.0f,
            2.0f
        )
        val scaleYAnimator =
            ObjectAnimator.ofFloat(
                binding.tipsNumberAnimation,
                "scaleY",
                1.0f,
                2.0f
            )

        // Crie uma animação de transparência
        val alphaAnimator = ObjectAnimator.ofFloat(
            binding.tipsNumberAnimation,
            "alpha",
            1.0f,
            0.0f
        )

        scaleXAnimator.duration = 1500
        scaleYAnimator.duration = 1500
        alphaAnimator.duration = 1500

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)

        binding.tipsNumberAnimation.visibility = TextView.VISIBLE

        animatorSet.start()
    }

    private fun configDateButtons() {
        binding.dateButton.setOnClickListener {
            binding.dateButton.visibility = View.GONE
            teamCrestAdapter.forEach {
                it.changeYearsPlayedVisibility()
            }
        }
    }

    private fun configConfirmationButton() {
        binding.confirmationButton.setOnClickListener {
            // TODO
        }
    }

    private fun showCustomDialog(tipsNumber: Int, tipsMessage: List<String>) {
        val dialog = Dialog(this, R.style.CustomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog_whos_player_tips)
        dialog.setCancelable(true)

        val closeButton = dialog.findViewById<ImageButton>(R.id.dialog_close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        val tipsContainer = dialog.findViewById<LinearLayout>(R.id.tipsContainer)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams

        val messages = mutableListOf<Pair<String, String>>()
        for (position in tipsMessage.size downTo tipsNumber) {
            messages.add(
                Pair(
                    this.getString(
                        R.string.whos_player_custom_tips_text_title,
                        (messages.size + NUMBER_ONE).toString()
                    ),
                    tipsMessage[messages.size]
                )
            )
        }

        for (tip in messages) {
            val firstTip = CustomTipsTextView(this)
            firstTip.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            (firstTip.layoutParams as LinearLayout.LayoutParams).setMargins(
                resources.getDimension(R.dimen.whos_player_0dp).toInt(),
                resources.getDimension(R.dimen.whos_player_12dp).toInt(),
                resources.getDimension(R.dimen.whos_player_0dp).toInt(),
                resources.getDimension(R.dimen.whos_player_0dp).toInt()
            )
            firstTip.setTexts(tip.first, tip.second)
            tipsContainer.addView(firstTip)
        }

        dialog.show()
    }

    companion object {
        const val spanCount = 3
        private const val soccerPlayerValue = 2

        private const val FIRST_INDEX = 0
        private const val NUMBER_ONE = 1
        private const val NEGATIVE_NUMBER_ONE = -1
    }
}
