package ru.ratings.tselikova.model

import androidx.room.*
import ru.ratings.tselikova.model.entities.ItemDbEntity

@Dao
interface ItemsDao {
    @Query("INSERT INTO items (rating, name, listId) VALUES (:rating, :name, :listId)")
    suspend fun createItem(rating: Int, name: String, listId: Int)

    @Update
    suspend fun updateItemData(item: ItemDbEntity)

    @Delete
    suspend fun deleteItem(item: ItemDbEntity)

    @Query("SELECT * FROM items JOIN lists ON items.listId = lists.id WHERE lists.name = :listname")
    suspend fun getAllByListname(listname: String): List<ItemDbEntity>

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<ItemDbEntity>

    @Query("SELECT * FROM items JOIN lists ON items.listId = lists.id WHERE lists.name = :listName AND items.name = :itemName")
    suspend fun getByListNameItemName(listName: String, itemName: String): ItemDbEntity?
}
