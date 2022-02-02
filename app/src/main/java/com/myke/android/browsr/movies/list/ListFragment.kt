package com.myke.android.browsr.movies.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.myke.android.browsr.base.BrowsrApplication
import com.myke.android.browsr.data.Movie
import com.myke.android.browsr.databinding.FragmentListBinding
import com.myke.android.browsr.di.ViewModelFactory
import com.myke.android.browsr.movies.MoviesActivity
import com.myke.android.browsr.utils.Constants.TAG
import com.myke.android.browsr.utils.Status
import com.myke.android.browsr.movies.MovieListViewModel
import javax.inject.Inject


class ListFragment : Fragment(), MovieListAdapter.OnItemClickListener {

    private val mAdapter = MovieListAdapter().apply { listener = this@ListFragment }

    private lateinit var binding: FragmentListBinding

    lateinit var movieListViewModel: MovieListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as BrowsrApplication).browsrComponent
            .moviesComponent().create().inject(this)

        movieListViewModel =
            ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: RecyclerView = binding.list

        list.adapter = mAdapter

        fetchMovies()

        val refresh = binding.refresh
        refresh.setOnClickListener {
            fetchMovies()
        }

        setupObserver()
    }

    private fun setupObserver() {
        movieListViewModel.movieList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { paginatedList -> renderList(paginatedList.results) }
                    binding.list.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.list.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.d(TAG, "Error retrieving online records")
                    binding.progressBar.visibility = View.GONE
                    (requireActivity() as MoviesActivity).showDialog(it.message, null)
                }
            }
        })

        movieListViewModel.errorMessage.observe(this, {
            (requireActivity() as MoviesActivity).showDialog(it, null)
        })
    }


    private fun renderList(movies: List<Movie>) {
        Log.i(TAG, "Populating UI with movies list...")
        mAdapter.submitList(movies)
        mAdapter.notifyItemRangeChanged(0, movies.size)
    }


    override fun onMovieItemClick(
        itemView: View,
        position: Int,
        movie: Movie
    ) {

        val action =
            ListFragmentDirections
                .actionMovieListFragmentToDetailFragment(movie.id)
        view?.findNavController()?.navigate(action)

    }

    private fun fetchMovies() {
        movieListViewModel.fetchMovies()
    }

}
