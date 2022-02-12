package com.myke.android.browsr.movies.detail

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.myke.android.browsr.BrowsrApplication
import com.squareup.picasso.Picasso
import com.myke.android.browsr.BuildConfig
import com.myke.android.browsr.R
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.databinding.FragmentDetailBinding
import com.myke.android.browsr.di.ViewModelFactory
import com.myke.android.browsr.movies.MoviesActivity
import com.myke.android.browsr.movies.MovieListViewModel
import com.myke.android.browsr.util.Constants
import com.myke.android.browsr.util.Status
import com.myke.android.browsr.util.loadImageUrl
import javax.inject.Inject


class DetailFragment : Fragment() {


    val args: DetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding


    lateinit var movieListViewModel: MovieListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BrowsrApplication).browsrComponent.moviesComponent()
            .create().inject(this)

        movieListViewModel =
            ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId
        fetchSelectedMovies(movieId)
        setupObserver()
    }

    private fun setupObserver() {
        movieListViewModel.movie.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movie -> showMovieDetails(movie) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Log.d(Constants.TAG, "Error retrieving online records")
                    binding.progressBar.visibility = View.GONE
                    (requireActivity() as MoviesActivity).showDialog(it.message, null)
                }
            }
        })

        movieListViewModel.errorMessage.observe(this, {
            (requireActivity() as MoviesActivity).showDialog(it, null)
        })
    }

    fun showMovieDetails(movie: Movie) {
        binding.title.text = movie.title
        binding.releaseDate.text = String.format(
            "%s%s", getString(R.string.release_date),
            DateFormat.format("yyyy", movie.releaseDate)
        )
        binding.rating.text =
            String.format("%s%s", getString(R.string.rating), "${movie.voteAverage ?: 0}")
        binding.description.text = movie.overview

        if (movie.posterPath != null) {
            binding.movieImage.loadImageUrl(BuildConfig.IMAGES_URL + movie.posterPath, null)
        } else {
            Picasso.get().load(R.drawable.movie)
                .noFade()
                .into(binding.movieImage)
        }
    }

    private fun fetchSelectedMovies(id: Long) {
        movieListViewModel.fetchSelectedMovies(id)
    }

}
