package com.samsdk.mvptask.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsdk.mvptask.R
import com.samsdk.mvptask.adapter.PostAdapter
import com.samsdk.mvptask.databinding.ActivityMainBinding
import com.samsdk.mvptask.model.Post
import com.samsdk.mvptask.presenter.MainPresenter
import com.samsdk.mvptask.view.MainView
import swipe.gestures.GestureManager

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        initViews()

    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        mainPresenter.apiPostList()

        itemTouchHelper()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun itemTouchHelper() {
        val leftCallBack = GestureManager.SwipeCallbackLeft {
            adapter.notifyItemRemoved(it)
            toast("Deleted")
        }
        val rightCallback = GestureManager.SwipeCallbackRight {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("title",adapter.getList()[it].title)
            intent.putExtra("body",adapter.getList()[it].body)
            startActivity(intent)
            adapter.notifyDataSetChanged()
        }
        val rvSwipe = GestureManager(rightCallback, leftCallBack)
        val itemTouchHelper = ItemTouchHelper(rvSwipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        rvSwipe.setIconRight(ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24))
        rvSwipe.setIconLeft(ContextCompat.getDrawable(this, R.drawable.ic_baseline_delete_outline_24))
        rvSwipe.setBackgroundColorRight(ColorDrawable(Color.parseColor("#D82E2F")))
    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        adapter = PostAdapter(posts)
        binding.recyclerView.adapter = adapter
    }

    override fun onPostListSuccess(posts: ArrayList<Post>) {
        refreshAdapter(posts)
    }

    override fun onPostListFailure(error: String) {
        toast(error)
    }

    override fun onPostUpdateSuccess(post: Post?) {
        toast("Updated")
    }

    override fun onPostUpdateFailure(error: String) {
        toast(error)
    }

    override fun onPostDeleteSuccess(post: Post?) {
        toast("Deleted")
    }

    override fun onPostDeleteFailure(error: String) {
        toast(error)
    }
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}