package com.android.noticeboard.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.android.noticeboard.core.BaseActivity
import com.android.noticeboard.core.extensions.getSerializableWithType
import com.android.noticeboard.domain.entity.Post
import com.android.noticeboard.home.R
import com.android.noticeboard.home.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity: BaseActivity<ActivityPostBinding>(R.layout.activity_post) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        val post = intent.getSerializableWithType(POST_DATA, Post::class.java)
        binding.post = post
    }

    companion object {
        private const val POST_DATA = "post_data"

        fun intent(context: Context, post: Post): Intent {
            return Intent(context, PostActivity::class.java)
                .putExtra(POST_DATA, post)
        }
    }

}
