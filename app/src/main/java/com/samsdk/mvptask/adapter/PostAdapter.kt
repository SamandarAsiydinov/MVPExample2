package com.samsdk.mvptask.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsdk.mvptask.databinding.ItemPostListBinding
import com.samsdk.mvptask.model.Post

class PostAdapter(
    private val items: ArrayList<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = items[position]

        holder.binding.apply {
            textTitle.text = post.title
            textBody.text = post.body
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getList(): ArrayList<Post> {
        return items
    }

    inner class PostViewHolder(val binding: ItemPostListBinding) :
        RecyclerView.ViewHolder(binding.root)
}