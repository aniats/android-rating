package ru.ratings.tselikova.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.ratings.tselikova.model.RatingService

class CreateNewListViewModel(private val ratingService: RatingService): ViewModel() {
    fun createNewList(name: String) {
        runBlocking {
            ratingService.createNewList(name)
        }
    }
}
