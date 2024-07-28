package com.sheraz.tweesty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheraz.tweesty.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private var repository: TweetRepository) : ViewModel() {

    val categories: StateFlow<List<String>> get() = repository.categories

    init {
        viewModelScope.launch {
            delay(500)
            repository.getCategories()
        }
    }

}