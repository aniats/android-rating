package ru.ratings.tselikova.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ratings.tselikova.model.RatingItem

@Entity(
    tableName = "items",
)
data class ItemDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "listId") val listId: Int,
) {
    fun toRatingItem(): RatingItem = RatingItem(
        name = name,
        rating = rating,
        id = id,
        listId = listId
    )

     companion object {
         fun fromRatingItem(ratingItem: RatingItem): ItemDbEntity = ItemDbEntity(
             id = ratingItem.id,
             rating = ratingItem.rating,
             name = ratingItem.name,
             listId = ratingItem.listId
         )
     }
}
