package ru.ratings.tselikova.screens.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import ru.ratings.tselikova.model.RatingItem
import ru.ratings.tselikova.model.RatingService

class ItemEditingViewModel(private val ratingService: RatingService): ViewModel() {
    fun updateElem(id: Int, newName: String, newRating: Int, listname: String, listId: Int) {
        runBlocking {
            val ri = RatingItem(name = newName, rating = newRating, id = id, listId = listId)
            ratingService.updateElem(listname, ri)
        }
    }

    fun deleteItem(item: RatingItem) {
        runBlocking {
            ratingService.deleteItem(item)
        }
    }
}