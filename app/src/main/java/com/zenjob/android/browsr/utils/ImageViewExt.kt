package com.zenjob.android.browsr.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zenjob.android.browsr.R

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