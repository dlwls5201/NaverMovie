package com.tistory.black_jin0427.navermovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tistory.black_jin0427.navermovie.R
import com.tistory.black_jin0427.navermovie.api.model.Movie
import com.tistory.black_jin0427.navermovie.databinding.ItemMovieBinding
import com.tistory.black_jin0427.navermovie.api.model.MovieItem


class MainAdapter: RecyclerView.Adapter<MainAdapter.MovieHolder>() {

    private var clickListener: OnItemClickListener? = null

    private var items = listOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = items[position]

        holder.binding?.let {
            it.item = item
            it.listener = clickListener
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

        val binding: ItemMovieBinding? = DataBindingUtil.bind(itemView)

    }
}

