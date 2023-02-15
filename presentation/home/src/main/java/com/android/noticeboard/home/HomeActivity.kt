package com.android.noticeboard.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.android.noticeboard.core.BaseActivity
import com.android.noticeboard.domain.entity.Post
import com.android.noticeboard.home.databinding.ActivityHomeBinding
import com.android.noticeboard.home.detail.PostActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeActivity: BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter by lazy {
        PostListAdapter(
            onItemClick = { post -> startPostDetailActivity(post) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        initView()
        observe()
    }

    private fun initView() {
        binding.rvPost.itemAnimator = null
        binding.rvPost.adapter = adapter

        val divider = DividerItemDecoration(this@HomeActivity, VERTICAL)
        binding.rvPost.addItemDecoration(divider)

    }

    private fun observe() {
        viewModel.state.onEach { state ->
            if(state.isInitialized) {
                adapter.submitList(state.posts)
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startPostDetailActivity(post: Post) {
        val intent = PostActivity.intent(this, post)
        startActivity(intent)
    }
}
