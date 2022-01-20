package com.zenjob.android.browsr.ui.list

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ActivityListBinding
import com.zenjob.android.browsr.ui.detail.DetailActivity
import com.zenjob.android.browsr.utils.Constants.TAG
import com.zenjob.android.browsr.utils.Status
import com.zenjob.android.browsr.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.zenjob.android.browsr.utils.EspressoIdlingResource


class ListActivity : AppCompatActivity(), MovieListAdapter.OnItemClickListener {

    val mAdapter = MovieListAdapter().apply { listener = this@ListActivity }

    private lateinit var binding: ActivityListBinding
    private val movieListViewModel: MovieListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val list: RecyclerView = findViewById(R.id.list)

        list.adapter = mAdapter

        fetchMovies()

        val refresh = findViewById<View>(R.id.refresh)
        refresh.setOnClickListener {
            fetchMovies()
        }

        setupObserver()
    }


    private fun setupObserver() {
        movieListViewModel.movieList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    if(BuildConfig.DEBUG) EspressoIdlingResource.decrement()
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { paginatedList -> renderList(paginatedList.results) }
                    binding.list.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    if(BuildConfig.DEBUG) EspressoIdlingResource.increment()
                    binding.progressBar.visibility = View.VISIBLE
                    binding.list.visibility = View.GONE
                }
                Status.ERROR -> {
                    if(BuildConfig.DEBUG) EspressoIdlingResource.decrement()
                    Log.d(TAG, "Error retrieving online records")
                    binding.progressBar.visibility = View.GONE
                    showDialog(it.message, null)
                }
            }
        })

        movieListViewModel.errorMessage.observe(this, {
            showDialog(it, null)
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
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    private fun fetchMovies() {
        movieListViewModel.fetchMovies()
    }


    private fun showDialog(message: String?, listener: DialogInterface.OnClickListener?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.ok, listener)
        builder.show()
    }

}
