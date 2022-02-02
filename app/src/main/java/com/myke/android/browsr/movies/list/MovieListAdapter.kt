package com.myke.android.browsr.movies.list

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.myke.android.browsr.BuildConfig
import com.myke.android.browsr.R
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.utils.loadImageUrl


class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieDiffCallback()) {
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onMovieItemClick(
            itemView: View,
            position: Int,
            movie: Movie
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            inflater.inflate(
                R.layout.viewholder_movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, listener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieImage: ImageView = itemView.findViewById(R.id.poster)
        private val titleTv: TextView = itemView.findViewById(R.id.title)
        private val ratingTv: TextView = itemView.findViewById(R.id.rating)
        private val releaseDateTv: TextView = itemView.findViewById(R.id.release_date)

        fun bind(movie: Movie, listener: OnItemClickListener?) {
            if (movie.backdropPath != null) {
                movieImage.loadImageUrl(BuildConfig.IMAGES_URL + movie.backdropPath, R.drawable.movie)
            } else {
                Picasso.get().load(R.drawable.movie)
                    .noFade()
                    .into(movieImage)
            }

            titleTv.text = movie.title
            releaseDateTv.text = String.format(
                "%s%s", itemView.context.getString(R.string.release_date),
                DateFormat.format("yyyy", movie.releaseDate)
            )
            ratingTv.text = String.format(
                "%s%s",
                itemView.context.getString(R.string.rating),
                "${movie.voteAverage ?: 0}"
            )

            itemView.setOnClickListener {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    if (layoutPosition != RecyclerView.NO_POSITION) {
                        listener.onMovieItemClick(itemView, layoutPosition, movie)
                    }
                }
            }

        }
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
