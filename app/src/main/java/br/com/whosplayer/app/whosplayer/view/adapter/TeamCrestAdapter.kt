package br.com.whosplayer.app.whosplayer.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import coil.decode.SvgDecoder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import coil.load

class TeamCrestAdapter(private val context: Context, private val items: List<TeamModel>) :
    RecyclerView.Adapter<TeamCrestAdapter.ItemViewHolder>() {

    private var yearVisibility: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_whos_player_team_crest, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeYearsPlayedVisibility() {
        yearVisibility = true
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val crestTeam: ImageView = itemView.findViewById(R.id.crestTeam)
        private val yearsPlayed: TextView = itemView.findViewById(R.id.yearsPlayed)
        private val arrowRight: ImageView = itemView.findViewById(R.id.arrowRight)

        fun bind(model: TeamModel) {
            if (model.crest.endsWith("svg", ignoreCase = true)) {
                displaySVGImage(model.crest, crestTeam)
            } else {
                displayImage(model.crest, crestTeam)
            }
            yearsPlayed.text = model.year
            if (model.lastTeam) {
                arrowRight.visibility = View.INVISIBLE
            }

            if (yearVisibility) {
                yearsPlayed.visibility = View.VISIBLE
            } else {
                yearsPlayed.visibility = View.INVISIBLE
            }
        }
    }

    private fun displayImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.loading_animation)

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    private fun displaySVGImage(url: String, imageView: ImageView) {
        imageView.load(url) {
            decoderFactory { result, options, _ ->
                SvgDecoder(
                    result.source,
                    options
                )
            }.error(R.drawable.ic_error)
                .placeholder(R.drawable.loading_animation)
        }
    }
}
