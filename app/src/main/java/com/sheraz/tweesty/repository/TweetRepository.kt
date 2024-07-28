package com.sheraz.tweesty.repository

import com.sheraz.tweesty.api.TweetApi
import com.sheraz.tweesty.model.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private var tweetApi: TweetApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>> get() = _tweets

    suspend fun getTweets(category: String) {
        val result = tweetApi.getTweets("tweets[?(@.category==\"${category}\")]")
        if (result.isSuccessful && result.body() != null) {
            _tweets.emit(result.body()!!)
        }
    }

    suspend fun getCategories() {
        val result = tweetApi.getCategories()
        if (result.isSuccessful && result.body() != null) {
            _categories.emit(result.body()!!)
        }
    }
}