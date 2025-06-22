package com.littlelemon.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val image: String
)

@Dao
interface MenuItemDao{
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems(): LiveData<List<MenuItem>>  // ohne suspend!
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItemsNow(): List<MenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMenuItem(item: MenuItem)

    @Delete
    suspend fun deleteMenuItem(menuItem: MenuItem)
}

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "menu.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}

