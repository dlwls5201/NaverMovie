package com.tistory.black_jin0427.navermovie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.model.MovieItem

@BindingAdapter("android:url")
fun ImageView.setImageByGlide(url: String) {
    setImageWithGlide(url)
}

@BindingAdapter("bind_adapter")
fun setBindAdapter(view: RecyclerView, adapter: MainAdapter?) {
    adapter?.let {
        view.adapter = it
    }
}

@BindingAdapter("bind_items")
fun setBindItems(view : RecyclerView, items : List<MovieItem>?) {
    items?.let {

        val adapter = view.adapter as MainAdapter
        adapter.setItems(items)
        adapter.notifyDataSetChanged()

    }
}