package br.com.whosplayer.commons.view
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import br.com.whosplayer.R

class CustomTipsTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleTextView: TextView
    private val valueTextView: TextView

    init {
        inflate(context, R.layout.custom_tips_text_view, this)

        titleTextView = findViewById(R.id.tipsTitle)
        valueTextView = findViewById(R.id.tipsValue)
    }

    fun setTexts(title: String, value: String) {
        titleTextView.text = title
        valueTextView.text = value
    }
}
