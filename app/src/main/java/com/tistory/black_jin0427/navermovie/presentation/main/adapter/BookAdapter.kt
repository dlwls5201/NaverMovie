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
import com.tistory.black_jin0427.navermovie.presentation.model.BookItem
import javax.inject.Inject

@ActivityScope
class BookAdapter @Inject constructor(): RecyclerView.Adapter<BookAdapter.MovieHolder>() {

    private var clickListener: OnItemClickListener? = null

    private var items = mutableListOf<BookItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = items[position]

        with(holder) {

            ivItemMovie.setImageWithGlide(item.image)
            tvItemBookTitle.setTextFromHtml(item.title)

            itemView.setOnClickListener {
                clickListener?.onClick(item)
            }
        }
    }

    fun setItems(items: MutableList<BookItem>) {
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
        fun onClick(bookItem: BookItem)
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivItemMovie: ImageView = itemView.findViewById(R.id.ivItemBook)
        val tvItemBookTitle: TextView = itemView.findViewById(R.id.tvItemBookTitle)
    }
}

