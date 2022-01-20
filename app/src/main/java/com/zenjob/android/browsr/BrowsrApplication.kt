package com.zenjob.android.browsr


import android.app.Application
import com.zenjob.android.browsr.di.appModule
import com.zenjob.android.browsr.di.repoModule
import com.zenjob.android.browsr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class BrowsrApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BrowsrApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}