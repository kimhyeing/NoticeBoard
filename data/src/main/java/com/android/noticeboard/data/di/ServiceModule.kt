package com.android.noticeboard.data.di

import android.content.Context
import com.android.noticeboard.data.service.PostService
import com.android.noticeboard.data.service.Service
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun providesPostService(
        retrofit: Retrofit
    ): PostService = retrofit.create()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("OKHttp").d(message)
        }

        return Service.retroBuilder(logInterceptor)
    }
}
