package ru.ratings.tselikova.model

import androidx.room.*
import ru.ratings.tselikova.model.entities.ListDbEntity

@Dao
interface ListsDao {
    @Insert(entity = ListDbEntity::class)
    suspend fun createList(listDbEntity: ListDbEntity)

    @Query("SELECT * FROM lists WHERE name = :listName")
    suspend fun getByName(listName: String): ListDbEntity?

    @Query("SELECT * FROM lists")
    suspend fun getAllLists(): List<ListDbEntity>

    @Query("DELETE FROM lists WHERE id = :id")
    suspend fun deleteById(id: Int)
}