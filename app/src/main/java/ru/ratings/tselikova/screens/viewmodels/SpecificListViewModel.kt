package ru.ratings.tselikova.screens.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.ratings.tselikova.model.RatingItem
import ru.ratings.tselikova.model.RatingService

class SpecificListViewModel(private val ratingService: RatingService): ViewModel() {

    private val _ratingItems = MutableLiveData<List<RatingItem>>()
    val ratingItems: LiveData<List<RatingItem>> = _ratingItems
    var listId = -1

    fun loadList(listname: String) {
        runBlocking {
            val (result, _listId) = ratingService.findByListname(listname)
            _ratingItems.value = result
            listId = _listId
        }
    }

    fun deleteList(listId: Int) {
        runBlocking {
            ratingService.deleteList(listId)
        }
    }

    fun requestNewId(): Int {
        return 0
    }
}
