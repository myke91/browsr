package com.myke.android.browsr


import android.app.Application
import com.myke.android.browsr.di.AppComponent
import com.myke.android.browsr.di.DaggerAppComponent

class BrowsrApplication : Application() {

    private fun initDagger(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)

    val browsrComponent: AppComponent by lazy {
        initDagger()
    }

}