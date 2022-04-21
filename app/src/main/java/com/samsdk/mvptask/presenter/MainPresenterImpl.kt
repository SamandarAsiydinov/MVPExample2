package com.samsdk.mvptask.presenter

import com.samsdk.mvptask.model.Post

interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
    fun apiPostUpdate(post: Post)
}