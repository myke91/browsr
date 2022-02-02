package com.myke.android.browsr.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myke.android.browsr.di.annotation.ViewModelKey
import com.myke.android.browsr.movies.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun moviesViewModel(viewModel: MovieListViewModel): ViewModel

}
