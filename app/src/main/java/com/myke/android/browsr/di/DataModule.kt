package com.myke.android.browsr.di

import com.myke.android.browsr.network.MoviesApiImpl
import com.myke.android.browsr.data.datasource.MoviesDatasource
import com.myke.android.browsr.data.datasource.remote.MoviesRemoteDatasource
import com.myke.android.browsr.data.repository.DefaultMovieRepository
import com.myke.android.browsr.data.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideMoviesRepository(datasource: MoviesRemoteDatasource): MovieRepository = DefaultMovieRepository(datasource)

    @Provides
    fun provideMoviesDatasource(moviesApi: MoviesApiImpl): MoviesDatasource = MoviesRemoteDatasource(moviesApi)
}