package com.example.demo_list_tweets.data.repository

import com.example.demo_list_tweets.data.remote.TweetsRemoteApi
import com.example.demo_list_tweets.domain.entity.Tweet
import com.example.demo_list_tweets.domain.repository.TweetRepository

class TweetsRemoteApiImpl(private val remoteApi: TweetsRemoteApi): TweetRepository {
    override suspend fun getTweetsByKeyWordAsync(keyword: String, tweetsCount: Int, language: String): Tweet {
        return remoteApi.getTweetsByKeyWordAsync(keyword = keyword, count = tweetsCount, lang = language)
    }
}