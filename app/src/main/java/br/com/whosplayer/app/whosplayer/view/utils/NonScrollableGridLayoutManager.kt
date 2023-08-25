package br.com.whosplayer.app.whosplayer.view.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class NonScrollableGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}
