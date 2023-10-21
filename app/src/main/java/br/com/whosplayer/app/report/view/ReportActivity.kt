package br.com.whosplayer.app.report.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }


    companion object {

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, ReportActivity::class.java)
    }
}
