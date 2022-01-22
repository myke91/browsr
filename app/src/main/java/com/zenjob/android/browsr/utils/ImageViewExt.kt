package com.zenjob.android.browsr.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zenjob.android.browsr.R

fun ImageView.loadImageUrl(url: String, needPlaceholder: Boolean) {
    if (needPlaceholder) {
        Picasso.get().load(url)
            .placeholder(R.drawable.movie)
            .into(this)
    } else {
        Picasso.get().load(url)
            .into(this)
    }

}