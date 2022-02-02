package com.myke.android.browsr.base


import android.app.Application
import com.myke.android.browsr.di.*

class BrowsrApplication : Application() {

    private fun initDagger(app: BrowsrApplication): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)

    val browsrComponent: AppComponent by lazy {
        initDagger(this)
    }

}