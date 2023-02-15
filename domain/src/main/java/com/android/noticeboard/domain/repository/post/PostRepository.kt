package com.android.noticeboard.domain.repository.post

import com.android.noticeboard.domain.entity.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}
