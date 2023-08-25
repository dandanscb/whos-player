package br.com.whosplayer.app.whosplayer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class TeamCrestAdapter(private val context: Context, private val items: List<TeamModel>) :
    RecyclerView.Adapter<TeamCrestAdapter.ItemViewHolder>() {

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

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val crestTeam: ImageView = itemView.findViewById(R.id.crestTeam)
        private val yearsPlayed: TextView = itemView.findViewById(R.id.yearsPlayed)
        private val arrowRight: ImageView = itemView.findViewById(R.id.arrowRight)

        fun bind(model: TeamModel) {
            displayImages(model.crest, crestTeam)
            yearsPlayed.text = model.year
            if (model.lastTeam) {
                arrowRight.visibility = View.INVISIBLE
            }
        }
    }

    private fun displayImages(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}