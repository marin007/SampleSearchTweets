package com.example.demo_list_tweets.data.repository

import com.example.demo_list_tweets.data.remote.TweetsRemoteApi
import com.example.demo_list_tweets.domain.entity.Tweet
import com.example.demo_list_tweets.domain.repository.TweetRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

class TweetsRemoteApiImpl(private val remoteApi: TweetsRemoteApi): TweetRepository {
    override suspend fun getTweetsByKeyWordAsync(keyword: String): Deferred<Response<Tweet>> {
        return remoteApi.getTweetsByKeyWordAsync(keyword = keyword)
    }
}