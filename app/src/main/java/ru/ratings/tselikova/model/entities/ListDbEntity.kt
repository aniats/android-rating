package ru.ratings.tselikova.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.ratings.tselikova.model.RatingList

@Entity(
    tableName = "lists",
     indices = [
         Index("name", unique = true)
     ]
)
data class ListDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
) {
    fun toRatingList(): RatingList = RatingList(
        name = name,
        id = id
    )

    companion object {
        fun fromRatingList(ratingList: RatingList) = ListDbEntity(
            id = ratingList.id,
            name = ratingList.name
        )
    }

}
