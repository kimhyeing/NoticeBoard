package com.android.noticeboard.data.remote.post

import com.android.noticeboard.data.service.PostService
import com.android.noticeboard.domain.entity.Post
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
): PostRemoteDataSource{
    override suspend fun getPosts(): List<Post> {
        return postService.getPosts()
    }
}
