package com.example.demo_list_tweets.domain.repository

import com.example.demo_list_tweets.domain.entity.Tweet
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface TweetRepository {
    suspend fun getTweetsByKeyWordAsync(keyword: String): Deferred<Response<Tweet>>
}