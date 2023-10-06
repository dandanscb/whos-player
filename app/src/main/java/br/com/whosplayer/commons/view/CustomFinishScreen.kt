package br.com.whosplayer.commons.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import br.com.whosplayer.R

class CustomFinishScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_finish_screen_view, this)
    }
}
