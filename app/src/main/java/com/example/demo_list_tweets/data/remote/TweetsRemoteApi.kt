package com.example.demo_list_tweets.data.remote

import com.example.demo_list_tweets.domain.entity.Tweet
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface TweetsRemoteApi {
    @GET("/1.1/search/tweets.json")
    @Headers("Content-Type: application/json")
    fun getTweetsByKeyWordAsync(@Query("q") keyword: String, @Query("count") count: String = "100", @Query("lang") lang: String = "en"): Deferred<Response<Tweet>>
}