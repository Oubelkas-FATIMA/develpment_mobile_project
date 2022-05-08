package com.example.myproject.network


import com.example.myproject.PostsResult
import retrofit2.http.GET
import retrofit2.Call

import retrofit2.http.Header
import retrofit2.http.Headers


interface PostsServices {
    @Headers("app-id:625ada62aec6fa0776e6b56c")
    @GET("post")
    fun getPostData(): Call<RetrofitResponse>
}