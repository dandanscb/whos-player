package br.com.whosplayer.app.whosplayer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R
import br.com.whosplayer.app.whosplayer.repository.mock.WhosPlayerMock
import br.com.whosplayer.app.whosplayer.view.adapter.TeamCrestAdapter
import br.com.whosplayer.app.whosplayer.view.utils.NonScrollableGridLayoutManager
import br.com.whosplayer.databinding.ActivityWhosPlayerBinding

class WhosPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayCrests()
    }

    private fun displayCrests() {
        val items = WhosPlayerMock.getStageModelMock()[2].soccerPlayer.teams
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

    companion object {
        const val spanCount = 3
    }
}
