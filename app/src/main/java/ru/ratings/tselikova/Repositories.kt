package ru.ratings.tselikova

import android.content.Context
import androidx.room.Room
import ru.ratings.tselikova.model.RatingService
import ru.ratings.tselikova.model.room.AppDatabase

object Repositories {
    private lateinit var applicationContext: Context

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .build()
    }

    val ratingService by lazy {
        RatingService(database.getItemsDao(), database.getListsDao())
    }

    fun init(context: Context) {
        applicationContext = context
    }
}