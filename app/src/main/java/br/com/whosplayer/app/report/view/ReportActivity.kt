package br.com.whosplayer.app.report.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.whosplayer.R
import br.com.whosplayer.databinding.ActivityWhosPlayerReportBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWhosPlayerReportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityWhosPlayerReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }


    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, ReportActivity::class.java)
    }
}
