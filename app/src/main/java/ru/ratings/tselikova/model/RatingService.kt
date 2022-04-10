package ru.ratings.tselikova.model

import android.util.Log
import ru.ratings.tselikova.RatingException
import ru.ratings.tselikova.model.entities.ItemDbEntity
import ru.ratings.tselikova.model.entities.ListDbEntity

data class RatingItem(val name: String, val rating: Int, val id: Int, val listId: Int)

data class RatingList(val name: String, val id: Int)

data class ListWithId(val list: List<RatingItem>, val id: Int)

class RatingService(val itemsDao: ItemsDao, val listsDao: ListsDao) {
    suspend fun createNewList(name: String) {
        if (listsDao.getByName(name) != null) {
            throw RatingException("List $name already exists")
        }
        val tmpList = RatingList(name, 0)

        listsDao.createList(ListDbEntity.fromRatingList(tmpList))
    }

    suspend fun updateElem(listname: String, item: RatingItem) {
        val gotItem = itemsDao.getByListNameItemName(listname, item.name)

        if (gotItem == null) {
            itemsDao.createItem(item.rating, item.name, item.listId)
        } else {
            itemsDao.updateItemData(ItemDbEntity.fromRatingItem(item))
        }
    }

    suspend fun findByListname(listname: String): ListWithId {
        val list = listsDao.getByName(listname)
            ?: throw RatingException("Asked for $listname when it does not exist")
        val itemsOfList = itemsDao.getAllItems().map {
            it.toRatingItem()
        }

        // TODO we actually can sort when selecting data from a table
        return ListWithId(itemsOfList.sortedWith(
            compareByDescending {
                it.rating
            }
        ), list.id)
    }

    suspend fun getListNames(): List<String> {
        return listsDao.getAllLists().map { it.name }
    }

    suspend fun deleteItem(item: RatingItem) {
        itemsDao.deleteItem(ItemDbEntity.fromRatingItem(item))
    }

    suspend fun deleteList(id: Int) {
        listsDao.deleteById(id)
    }
}