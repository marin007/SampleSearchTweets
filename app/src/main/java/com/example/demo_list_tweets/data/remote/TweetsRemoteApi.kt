package com.example.demo_list_tweets.data.remote

import com.example.demo_list_tweets.domain.entity.Tweet
import retrofit2.http.*

interface TweetsRemoteApi {
    @GET("/1.1/search/tweets.json")
    @Headers("Content-Type: application/json")
    suspend fun getTweetsByKeyWordAsync(@Query("q") keyword: String, @Query("count") count: Int, @Query("lang") lang: String): Tweet
}