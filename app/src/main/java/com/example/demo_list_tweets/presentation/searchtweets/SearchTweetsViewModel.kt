package com.example.demo_list_tweets.presentation.searchtweets

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_list_tweets.domain.entity.Tweet
import com.example.demo_list_tweets.domain.usecases.GetTweetByKeywordUseCase
import com.example.demo_list_tweets.presentation.common.ValueWrapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchTweetsViewModel(private val tweetRepository: GetTweetByKeywordUseCase) : ViewModel() {

    val uiState: MutableLiveData<ValueWrapper<UiState>> by lazy {
        MutableLiveData<ValueWrapper<UiState>>()
    }

    val event: MutableLiveData<ValueWrapper<Event>> by lazy {
        MutableLiveData<ValueWrapper<Event>>()
    }

    val tweets: MutableLiveData<Tweet> by lazy {
        MutableLiveData<Tweet>()
    }

    var keyword: ObservableField<String> = ObservableField()

    var showLoader: ObservableBoolean = ObservableBoolean(false)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        event.value = ValueWrapper(
            Event.Error(
                throwable.message
            )
        )
    }

    fun searchTweet() {
        keyword.get()?.let {
            getTweets(it)
        }

    }

    fun getTweets(keyword: String) {
        uiState.value = ValueWrapper(UiState.FetchingTweets)
        viewModelScope.launch(coroutineExceptionHandler) {
            tweets.value = tweetRepository.getTweetsByKeyWordAsync(keyword = keyword, tweetsCount = TWEETS_COUNT, language = LANGUAGE)
        }
    }

    sealed class UiState {
        object FetchingTweets : UiState()
    }

    sealed class Event {
        data class Error(val message: String?) : Event()
        data class SearchTweets(val keyword: String) : Event()
    }

    companion object {
        const val TWEETS_COUNT = 100
        const val LANGUAGE = "en"
    }
}