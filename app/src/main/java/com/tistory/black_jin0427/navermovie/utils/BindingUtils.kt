package com.tistory.black_jin0427.navermovie.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.black_jin0427.navermovie.adapter.MainAdapter
import com.tistory.black_jin0427.navermovie.api.model.MovieItem
import com.tistory.black_jin0427.navermovie.setImageWithGlide
import com.tistory.black_jin0427.navermovie.setTextFromHtml
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter.OnQueryTextChange
import androidx.databinding.adapters.SearchViewBindingAdapter.OnQueryTextSubmit



@BindingAdapter("android:url")
fun ImageView.setImageByGlide(url: String) {
    setImageWithGlide(url)
}

@BindingAdapter("android:htmlText")
fun TextView.setHtmlText(text: String) {
    setTextFromHtml(text)
}

@BindingAdapter("android:query")
fun SearchView.setQuery(query: String?) {
    setQuery(query, false)
}


@BindingAdapter("app:bind_adapter")
fun RecyclerView.setBindAdapter(adapter: MainAdapter?) {
    adapter?.let {
        this.adapter = it
    }
}

@BindingAdapter("app:bind_items")
fun RecyclerView.setBindItems(items : List<MovieItem>?) {
    items?.let {
        val adapter = this.adapter as MainAdapter
        adapter.setItems(items)
        adapter.notifyDataSetChanged()
    }
}

