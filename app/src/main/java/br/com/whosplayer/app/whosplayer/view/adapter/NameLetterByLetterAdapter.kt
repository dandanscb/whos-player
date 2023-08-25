package br.com.whosplayer.app.whosplayer.view.adapter

import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R

class NameLetterByLetterAdapter(private val recyclerViewPosition: Int, private val items: List<Char>) :
    RecyclerView.Adapter<NameLetterByLetterAdapter.ItemViewHolder>() {
    var editTextFocusListener: EditTextFocusListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_whos_player_name_letters, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // not used yet
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: EditText = itemView.findViewById(R.id.letterEditText)

        init {
            editText.filters = arrayOf<InputFilter>(AllCaps())

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) { // not used yet
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty() && s[0].isLetter()) {
                        editTextFocusListener?.onLetterTyped(recyclerViewPosition, adapterPosition + 1)
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    s?.let {
                        if (it.length > 1) {
                            it.delete(1, it.length)
                        }
                    }
                }
            })

            editText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editText.text.toString().isNullOrEmpty()) {
                        editTextFocusListener?.onLetterTyped(recyclerViewPosition, adapterPosition - 1)
                    }
                }
                false
            }
        }
    }

    interface EditTextFocusListener {
        fun onLetterTyped(recyclerViewPosition: Int, position: Int)
    }
}
