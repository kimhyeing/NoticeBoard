package com.android.noticeboard.data.service

import com.android.noticeboard.data.extensions.createInterceptors
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Service {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/posts/"

        internal fun retroBuilder(vararg interceptor: Interceptor): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createInterceptors(interceptor.toList()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
