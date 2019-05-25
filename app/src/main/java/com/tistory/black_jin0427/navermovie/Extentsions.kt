package com.tistory.black_jin0427.navermovie

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.setImageWithGlide(url: String?) =
    url?.let {
        try {
            Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .into(this)
        } catch (ignore: Exception) { }
    }