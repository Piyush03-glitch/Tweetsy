package com.example.tweetsy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.model.TweetsListItem
import com.example.tweetsy.repo.TweetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TweetsViewModel @Inject constructor(private val tweetsRepository: TweetsRepository) : ViewModel(){
    val tweets : StateFlow<List<TweetsListItem>>
        get() = tweetsRepository.tweets

    init {
        viewModelScope.launch {
            tweetsRepository.getTweets("motivation")
        }
    }
}