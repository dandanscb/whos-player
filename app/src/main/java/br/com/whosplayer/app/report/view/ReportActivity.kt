package br.com.whosplayer.app.report.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
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

        val spannableString = SpannableString(binding.whosPlayerEmail.text)

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun onClick(textView: View) {
                    // ToDo
                }
            }, START_INDEX, binding.whosPlayerEmail.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.whosPlayerEmail.text = spannableString
        binding.whosPlayerEmail.movementMethod = LinkMovementMethod.getInstance()
    }


    companion object {

        private const val START_INDEX = 0

        @JvmStatic
        fun newInstance(context: Context): Intent = Intent(context, ReportActivity::class.java)
    }
}
