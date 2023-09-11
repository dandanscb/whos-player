package br.com.whosplayer.app.whosplayer.view

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
import br.com.whosplayer.app.whosplayer.repository.mock.WhosPlayerMock
import br.com.whosplayer.app.whosplayer.view.adapter.NameLetterByLetterAdapter
import br.com.whosplayer.app.whosplayer.view.adapter.TeamCrestAdapter
import br.com.whosplayer.app.whosplayer.view.utils.NonScrollableGridLayoutManager
import br.com.whosplayer.databinding.ActivityWhosPlayerBinding
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.core.view.marginTop
import br.com.whosplayer.app.whosplayer.repository.mock.WhosPlayerMock.getTipsMessages
import br.com.whosplayer.app.whosplayer.repository.model.StageModel
import br.com.whosplayer.commons.view.CustomTipsTextView

class WhosPlayerActivity : AppCompatActivity(), NameLetterByLetterAdapter.EditTextFocusListener {

    private lateinit var binding: ActivityWhosPlayerBinding
//    val viewModel: WhosPlayerViewModel by viewModels()

    private var teamCrestAdapter = mutableListOf<TeamCrestAdapter>()

    private var nameLetterByLetterAdapter = mutableListOf<NameLetterByLetterAdapter>()
    private var recyclerViewReference = mutableListOf<RecyclerView>()

    private var player: StageModel = WhosPlayerMock.getStageModelMock()[soccerPlayerValue]
    private var remainingTips: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        displayCrests()
        showFieldForLetters()
        configTipsButtons()
        configDateButtons()
    }

    private fun initViewModel() {
//        viewModel.getStages()
//
//        viewModel.stage.observe(this, Observer {
//            when (it) {
//
//            }
//        })
    }

    private fun displayCrests() {
        val items = player.soccerPlayer.teams
        items.forEach {
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

    private fun showFieldForLetters() {
        var index = FIRST_INDEX
        val items = player.soccerPlayer.nameLetterByLetter
        items.forEach {
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

    private fun configTipsButtons() {
        val tipsMessage = getTipsMessages(player.soccerPlayer.tips)
        remainingTips = tipsMessage.size

        binding.remainingHintNumbers.text = remainingTips.toString()

        binding.tipsButton.setOnClickListener {
            showCustomDialog(remainingTips, tipsMessage)
            if (remainingTips != 1) {
                remainingTips--
                binding.remainingHintNumbers.text = remainingTips.toString()
            } else {
                binding.remainingHintNumbers.text = (remainingTips - 1).toString()
            }
        }
    }

    private fun configDateButtons() {
        binding.dateButton.setOnClickListener {
            binding.dateButton.visibility = View.GONE
            teamCrestAdapter.forEach {
                it.changeYearsPlayedVisibility()
            }
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

        for (position in tipsNumber..tipsMessage.size) {
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
            firstTip.setTexts(
                this.getString(R.string.whos_player_custom_tips_text_title, position),
                tipsMessage[tipsMessage.size - position]
            )

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
