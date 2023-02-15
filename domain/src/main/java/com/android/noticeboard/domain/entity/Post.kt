package com.android.noticeboard.domain.entity

import java.io.Serializable

data class Post (
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
): Serializable
