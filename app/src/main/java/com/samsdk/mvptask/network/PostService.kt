package com.samsdk.mvptask.network


import com.samsdk.mvptask.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @Headers(
        "Content-type:application/json"
    )

    @GET("posts")
    fun listPost(): Call<ArrayList<Post>>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int): Call<Post>
}