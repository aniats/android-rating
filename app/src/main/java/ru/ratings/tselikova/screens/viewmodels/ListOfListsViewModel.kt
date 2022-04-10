package ru.ratings.tselikova.screens.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.ratings.tselikova.model.RatingService

class ListOfListsViewModel(private val ratingService: RatingService): ViewModel() {
    private val _ratings = MutableLiveData<List<String>>()
    val ratings: LiveData<List<String>> = _ratings

    fun loadLists() {
        runBlocking {
            _ratings.value = ratingService.getListNames()
        }
    }
}