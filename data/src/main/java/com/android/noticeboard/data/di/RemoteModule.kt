package com.android.noticeboard.data.di

import com.android.noticeboard.data.remote.post.PostRemoteDataSource
import com.android.noticeboard.data.remote.post.PostRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteModule {

    @Binds
    @Singleton
    abstract fun bindPostRemoteDataSource(
        dataSource: PostRemoteDataSourceImpl
    ): PostRemoteDataSource

}
