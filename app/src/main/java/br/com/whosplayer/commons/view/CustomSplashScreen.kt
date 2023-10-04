package br.com.whosplayer.commons.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.whosplayer.R

class CustomSplashScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView

    init {
        inflate(context, R.layout.custom_splash_screen_view, this)

        imageView = findViewById(R.id.custom_splash_screen_logo)
        imageView.alpha = 0f
        imageView.animate().setDuration(1500).alpha(1f)
    }
}
