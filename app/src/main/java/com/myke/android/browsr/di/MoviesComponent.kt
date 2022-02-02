package com.myke.android.browsr.di

import com.myke.android.browsr.di.annotation.ActivityScope
import com.myke.android.browsr.movies.MoviesActivity
import com.myke.android.browsr.movies.detail.DetailFragment
import com.myke.android.browsr.movies.list.ListFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MoviesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MoviesComponent
    }

    fun inject(activity: MoviesActivity)
    fun inject(fragment: ListFragment)
    fun inject(fragment: DetailFragment)
}