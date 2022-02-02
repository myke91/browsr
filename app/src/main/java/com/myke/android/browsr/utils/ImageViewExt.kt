package com.myke.android.browsr.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(url: String, placeholder: Int?) {
    placeholder?.let {
        Picasso.get().load(url)
            .placeholder(it)
            .into(this)
        return
    }
    Picasso.get().load(url)
        .into(this)
}