package com.android.noticeboard.home

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
    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            startLoading()
            kotlin.runCatching {
                postRepository.getPosts()
            }.onSuccess { posts ->
                _state.value = _state.value.copy(
                    isInitialized = true,
                    posts = posts
                )
                Timber.d("success : $posts")
            }.onFailure { throwable ->
                Timber.e("error : ${throwable.message}")
                _state.value = _state.value.copy(isInitialized = true)
                sendErrorMessage(throwable)
            }
            stopLoading()
        }
    }

    data class State(
        val isInitialized: Boolean = false,
        val posts: List<Post> = emptyList()
    )
}
