package com.tistory.black_jin0427.navermovie.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.di.scope.ActivityScope
import com.tistory.black_jin0427.navermovie.extension.setImageWithGlide
import com.tistory.black_jin0427.navermovie.extension.setTextFromHtml
import com.tistory.black_jin0427.navermovie.presentation.model.MovieItem
import javax.inject.Inject

@ActivityScope
class MovieAdapter @Inject constructor(): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var clickListener: OnItemClickListener? = null

    private var items = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = items[position]

        with(holder) {

            ivItemMovie.setImageWithGlide(item.image)

            tvItemMovieTitle.setTextFromHtml(item.title)
            tvItemMovieDirector.text = "감독 : " + item.director
            tvItemMovieActor.text = "배우 : " + item.actor
            tvItemMovieRating.text = "별점 : " + item.rating

            itemView.setOnClickListener {
                clickListener?.onClick(item)
            }
        }
    }

    fun setItems(items: MutableList<MovieItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    interface OnItemClickListener {
        fun onClick(movieItem: MovieItem)
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivItemMovie: ImageView = itemView.findViewById(R.id.ivItemMovie)
        val tvItemMovieTitle: TextView = itemView.findViewById(R.id.tvItemMovieTitle)
        val tvItemMovieDirector: TextView = itemView.findViewById(R.id.tvItemMovieDirector)
        val tvItemMovieActor: TextView = itemView.findViewById(R.id.tvItemMovieActor)
        val tvItemMovieRating: TextView = itemView.findViewById(R.id.tvItemMovieRating)
    }
}

