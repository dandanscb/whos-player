package br.com.whosplayer.commons.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.whosplayer.R

class CustomErrorScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val imageButton: ImageButton

    init {
        inflate(context, R.layout.custom_error_screen_view, this)

        imageButton = findViewById(R.id.close_button)
    }

    fun closeClickListener(clickListener: OnClickListener) {
        imageButton.setOnClickListener(clickListener)
    }
}
