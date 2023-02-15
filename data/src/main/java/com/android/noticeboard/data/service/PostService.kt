package com.android.noticeboard.data.service

import com.android.noticeboard.domain.entity.Post
import retrofit2.http.GET

interface PostService {
    @GET(".")
    suspend fun getPosts(): List<Post>
}
