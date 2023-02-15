package com.android.noticeboard.data.di

import com.android.noticeboard.data.repository.PostRepositoryImpl
import com.android.noticeboard.domain.repository.post.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsPostRepository(
        repositoryImpl: PostRepositoryImpl
    ): PostRepository

}
