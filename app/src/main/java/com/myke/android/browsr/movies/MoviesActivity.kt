package com.myke.android.browsr.movies

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import com.myke.android.browsr.base.BrowsrApplication
import com.myke.android.browsr.R
import com.myke.android.browsr.di.MoviesComponent
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesComponent: MoviesComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesComponent =
            (application as BrowsrApplication).browsrComponent.moviesComponent().create()

        moviesComponent.inject(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

     fun showDialog(message: String?, listener: DialogInterface.OnClickListener?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.ok, listener)
        builder.show()
    }

}