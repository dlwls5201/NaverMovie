package com.tistory.black_jin0427.navermovie.extension

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tistory.black_jin0427.navermovie.R

fun ImageView.setImageWithGlide(url: String?) =
    url?.let {
        try {
            Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .error(R.mipmap.ic_launcher_round)
                .into(this)
        } catch (ignore: Exception) {
        }
    }

fun TextView.setTextFromHtml(text: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(text)
    }
}