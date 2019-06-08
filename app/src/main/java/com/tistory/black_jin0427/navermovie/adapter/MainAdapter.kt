package com.tistory.black_jin0427.navermovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import com.tistory.black_jin0427.navermovie.setImageWithGlide
import com.tistory.black_jin0427.navermovie.setTextFromHtml


class MainAdapter: RecyclerView.Adapter<MainAdapter.MovieHolder>() {

    private var clickListener: OnItemClickListener? = null

    private var items = listOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = items[position]

        with(holder) {

            ivItemMovie.setImageWithGlide(item.image)

            tvItemMovieTitle.setTextFromHtml(item.title)
            tvItemMovieSubTitle.setTextFromHtml(item.subtitle)
            tvItemMovieLink.setTextFromHtml(item.link)
            tvItemMovieActor.text = item.actor
                .filter { it.toString() !=  "|" }

            itemView.setOnClickListener {
                clickListener?.onClick(item)
            }
        }
    }

    fun setItems(items: List<MovieItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    interface OnItemClickListener {
        fun onClick(movieItem: MovieItem)
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val ivItemMovie: ImageView = itemView.findViewById(R.id.ivItemMovie)
        val tvItemMovieTitle: TextView = itemView.findViewById(R.id.tvItemMovieTitle)
        val tvItemMovieSubTitle: TextView = itemView.findViewById(R.id.tvItemMovieSubTitle)
        val tvItemMovieLink: TextView = itemView.findViewById(R.id.tvItemMovieLink)
        val tvItemMovieActor: TextView = itemView.findViewById(R.id.tvItemMovieActor)
    }
}

