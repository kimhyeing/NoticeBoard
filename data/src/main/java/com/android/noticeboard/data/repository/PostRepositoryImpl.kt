package com.android.noticeboard.data.repository

import com.android.noticeboard.data.remote.post.PostRemoteDataSource
import com.android.noticeboard.domain.entity.Post
import com.android.noticeboard.domain.repository.post.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
): PostRepository {
    override suspend fun getPosts(): List<Post> {
        return postRemoteDataSource.getPosts()
    }
}
