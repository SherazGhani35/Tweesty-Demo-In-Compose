package com.sheraz.tweesty.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheraz.tweesty.model.TweetListItem
import com.sheraz.tweesty.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {

    val tweets: StateFlow<List<TweetListItem>> get() = repository.tweets

    init {
        viewModelScope.launch {
            delay(1000)
            val category = savedStateHandle.get<String>("category") ?: "food"
            repository.getTweets(category)
        }
    }

}