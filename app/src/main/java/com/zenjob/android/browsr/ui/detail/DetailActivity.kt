package com.zenjob.android.browsr.ui.detail

import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.utils.loadImageUrl


class DetailActivity : AppCompatActivity() {

    private val titleTv: TextView by lazy { findViewById<TextView>(R.id.title) }
    private val ratingTv: TextView by lazy { findViewById<TextView>(R.id.rating) }
    private val releaseDateTv: TextView by lazy { findViewById<TextView>(R.id.release_date) }
    private val descriptionTv: TextView by lazy { findViewById<TextView>(R.id.description) }
    private val movieImage: ImageView by lazy { findViewById<ImageView>(R.id.movie_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie = (if (intent.hasExtra("movie")) intent.getSerializableExtra("movie")
                as Movie else null) ?: return

        titleTv.text = movie.title
        releaseDateTv.text = String.format(
            "%s%s", getString(R.string.release_date),
            DateFormat.format("yyyy", movie.releaseDate)
        )
        ratingTv.text =
            String.format("%s%s", getString(R.string.rating), "${movie.voteAverage ?: 0}")
        descriptionTv.text = movie.overview

        if (movie.posterPath != null) {
            movieImage.loadImageUrl(BuildConfig.IMAGES_URL + movie.posterPath, null)
        } else {
            Picasso.get().load(R.drawable.movie)
                .noFade()
                .into(movieImage)
        }
    }

}
