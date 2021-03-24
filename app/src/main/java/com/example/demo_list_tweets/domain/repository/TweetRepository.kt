package com.example.demo_list_tweets.domain.repository

import com.example.demo_list_tweets.domain.entity.Tweet

interface TweetRepository {
    suspend fun getTweetsByKeyWordAsync(keyword: String, tweetsCount: Int, language: String): Tweet
}