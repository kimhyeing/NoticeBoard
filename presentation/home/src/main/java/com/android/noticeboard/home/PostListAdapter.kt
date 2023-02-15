package com.android.noticeboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.noticeboard.domain.entity.Post
import com.android.noticeboard.home.databinding.ItemPostBinding

class PostListAdapter(
    private val onItemClick: (post: Post) -> Unit
): ListAdapter<Post, PostListAdapter.PostItemViewHolder>(CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
       return PostItemViewHolder(
           binding = ItemPostBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           ),
           onItemClick = onItemClick
       )
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostItemViewHolder(
        private val binding: ItemPostBinding,
        private val onItemClick: (post: Post) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            binding.containerPost.setOnClickListener {
                if (::post.isInitialized) {
                    onItemClick.invoke(post)
                }
            }
        }

        fun bind(data: Post) {
            post = data
            binding.post = post
        }
    }

    companion object CALLBACK : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}
