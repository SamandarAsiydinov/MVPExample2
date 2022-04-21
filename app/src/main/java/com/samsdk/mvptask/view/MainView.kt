package com.samsdk.mvptask.view

import com.samsdk.mvptask.model.Post

interface MainView {

    fun onPostListSuccess(posts: ArrayList<Post>)
    fun onPostListFailure(error: String)

    fun onPostUpdateSuccess(post: Post?)
    fun onPostUpdateFailure(error: String)

    fun onPostDeleteSuccess(post: Post?)
    fun onPostDeleteFailure(error: String)

}