package com.sheraz.tweesty.api

import com.sheraz.tweesty.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TweetApi {

    @GET("/v3/b/66a3780aacd3cb34a86b8c66?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories(): Response<List<String>>


    @GET("/v3/b/66a3780aacd3cb34a86b8c66?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>
}