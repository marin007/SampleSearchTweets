package com.example.demo_list_tweets.domain.usecases

import com.example.demo_list_tweets.domain.entity.Tweet
import com.example.demo_list_tweets.domain.repository.TweetRepository

class GetTweetByKeywordUseCase(private val tweetRepository: TweetRepository) {
    suspend fun getTweetsByKeyWordAsync(keyword: String, tweetsCount: Int, language: String) : Tweet {
        return tweetRepository.getTweetsByKeyWordAsync(keyword = keyword, tweetsCount = tweetsCount, language = language)
    }
}