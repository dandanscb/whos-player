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

class WhosPlayerActivity : AppCompatActivity(), NameLetterByLetterAdapter.EditTextFocusListener {

    private lateinit var binding: ActivityWhosPlayerBinding
    private var nameLetterByLetterAdapter = mutableListOf<NameLetterByLetterAdapter>()
    private var recyclerViewReference = mutableListOf<RecyclerView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        displayCrests()
        showFieldForLetters()
    }

    private fun displayCrests() {
        val items = WhosPlayerMock.getStageModelMock()[soccerPlayerValue].soccerPlayer.teams
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
        }
    }

    private fun showFieldForLetters() {
        var index = FIRST_INDEX
        val items =
            WhosPlayerMock.getStageModelMock()[soccerPlayerValue].soccerPlayer.nameLetterByLetter
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
        menuInflater.inflate(R.menu.menu_toolbar, menu)
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

    companion object {
        const val spanCount = 3
        private const val soccerPlayerValue = 2

        private const val FIRST_INDEX = 0
        private const val NUMBER_ONE = 1
        private const val NEGATIVE_NUMBER_ONE = -1
    }
}
