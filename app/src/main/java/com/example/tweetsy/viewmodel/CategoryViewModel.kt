package com.example.tweetsy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.repo.TweetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val tweetsRepository: TweetsRepository) : ViewModel(){
    val category : StateFlow<List<String>>
        get() = tweetsRepository.categories

    init {
        viewModelScope.launch {
            tweetsRepository.getTweetsCategory()
        }
    }
}