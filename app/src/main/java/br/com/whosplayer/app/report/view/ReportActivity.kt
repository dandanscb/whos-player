package br.com.whosplayer.app.report.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.whosplayer.BuildConfig
import br.com.whosplayer.R
import br.com.whosplayer.databinding.ActivityWhosPlayerReportBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerReportBinding
    private var startY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.versionCode.text =
            getString(R.string.whos_player_report_screen_version_code, BuildConfig.VERSION_NAME)

        binding.closeButton.setOnClickListener {
            finish()
        }

        binding.reportEmailContainer.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(
                "Texto Copiado",
                getString(R.string.whos_player_report_screen_email)
            )
            clipboardManager.setPrimaryClip(clipData)

            Toast.makeText(this, "Texto copiado para a área de transferência", Toast.LENGTH_SHORT)
                .show()
        }

        binding.reportCardContainer.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startY = event.y
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val deltaY = event.y - startY
                        val newTopMargin = (view.top + deltaY).toInt()
                        view.layout(view.left, newTopMargin, view.right, newTopMargin + view.height)
                        startY = event.y
                    }
                }
                return true
            }
        })
    }


    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, ReportActivity::class.java)
    }
}
