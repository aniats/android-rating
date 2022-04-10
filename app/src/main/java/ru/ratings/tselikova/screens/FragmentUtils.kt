package ru.ratings.tselikova.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ratings.tselikova.Repositories
import ru.ratings.tselikova.model.RatingService
import ru.ratings.tselikova.screens.viewmodels.CreateNewListViewModel
import ru.ratings.tselikova.screens.viewmodels.ListOfListsViewModel
import ru.ratings.tselikova.screens.viewmodels.SpecificListViewModel
import ru.ratings.tselikova.screens.viewmodels.StartWindowViewModel
import ru.ratings.tselikova.screens.viewmodels.ItemEditingViewModel

class ViewModelFactory(
    private val ratingService: RatingService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            StartWindowViewModel::class.java -> {
                StartWindowViewModel()
            }
            CreateNewListViewModel::class.java -> {
                CreateNewListViewModel(ratingService)
            }
            ListOfListsViewModel::class.java -> {
                ListOfListsViewModel(ratingService)
            }
            SpecificListViewModel::class.java -> {
                SpecificListViewModel(ratingService)
            }
            ItemEditingViewModel::class.java -> {
                ItemEditingViewModel(ratingService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }

}

fun Fragment.factory() = ViewModelFactory(Repositories.ratingService)