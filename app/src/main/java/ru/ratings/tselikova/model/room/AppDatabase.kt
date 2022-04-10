package ru.ratings.tselikova.model.room
 
import ru.ratings.tselikova.model.ItemsDao
import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ratings.tselikova.model.ListsDao
import ru.ratings.tselikova.model.entities.ItemDbEntity
import ru.ratings.tselikova.model.entities.ListDbEntity

@Database(
    version = 1,
    entities = [
        ItemDbEntity::class,
        ListDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getItemsDao(): ItemsDao

    abstract fun getListsDao(): ListsDao

}
