package com.zenjob.android.browsr.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.data.DateJsonAdapter
import com.zenjob.android.browsr.network.ApiHelper
import com.zenjob.android.browsr.network.ApiHelperImpl
import com.zenjob.android.browsr.network.ApiService
import com.zenjob.android.browsr.network.NetworkHelper
import com.zenjob.android.browsr.repository.DefaultMovieRepository
import com.zenjob.android.browsr.repository.MovieRepository
import com.zenjob.android.browsr.viewmodel.MovieListViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    fun provideNetworkHelper(context: Context) = NetworkHelper(context)

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

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    }

    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

val repoModule = module {
    single<MovieRepository> {
        return@single DefaultMovieRepository(get())
    }
}

val viewModelModule = module {
    viewModel { MovieListViewModel(get(), get()) }
}

