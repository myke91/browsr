package com.myke.android.browsr.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.myke.android.browsr.BuildConfig
import com.myke.android.browsr.data.DateJsonAdapter
import com.myke.android.browsr.network.MoviesApi
import com.myke.android.browsr.network.MoviesApiImpl
import com.myke.android.browsr.network.MoviesService
import com.myke.android.browsr.network.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .build()

    }


    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val tmdbApiInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val reqBuilder = original.newBuilder()
                .url(url)


            chain.proceed(reqBuilder.build())
        }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient
                .Builder()
                .addInterceptor(tmdbApiInterceptor)
                .addInterceptor(loggingInterceptor)
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
        } else {
            return OkHttpClient
                .Builder()
                .addInterceptor(tmdbApiInterceptor)
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
        }

    }

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(
        MoviesService::class.java)

    @Provides
    fun provideNetworkHelper(context: Context) = NetworkHelper(context)

    @Provides
    fun provideApiHelper(moviesService: MoviesService): MoviesApi {
        return MoviesApiImpl(moviesService)
    }

}