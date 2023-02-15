package com.android.noticeboard.data.remote.post

import com.android.noticeboard.domain.entity.Post

interface PostRemoteDataSource {
    suspend fun getPosts(): List<Post>
}
