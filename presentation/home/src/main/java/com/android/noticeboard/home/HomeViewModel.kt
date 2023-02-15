package com.android.noticeboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.noticeboard.core.BaseViewModel
import com.android.noticeboard.domain.entity.Post
import com.android.noticeboard.domain.repository.post.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository
): BaseViewModel() {
    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts: StateFlow<List<Post>>
        get() = _posts.asStateFlow()

    fun getPosts() {
        viewModelScope.launch {
            kotlin.runCatching {
                postRepository.getPosts()
            }.onSuccess { response ->
                _posts.value = response
                Timber.d("게시글 : $response")
            }.onFailure { throwable ->
                Timber.e("${throwable.message}")
                sendErrorMessage(throwable)
            }
        }
    }
}
