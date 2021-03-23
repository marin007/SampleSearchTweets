package com.example.demo_list_tweets.domain.usecases

import com.example.demo_list_tweets.domain.entity.Tweet
import com.example.demo_list_tweets.domain.repository.TweetRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

class GetTweetByKeywordUseCase(private val tweetRepository: TweetRepository) {
    suspend fun getTweetsByKeyWordAsync(keyword: String) : Deferred<Response<Tweet>> {
        return tweetRepository.getTweetsByKeyWordAsync(keyword = keyword)
    }
}