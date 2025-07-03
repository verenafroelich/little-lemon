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
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val image: String,
    val category: String
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

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE MenuItem ADD COLUMN category TEXT NOT NULL DEFAULT ' '")
    }
}

@Database(entities = [MenuItem::class], version = 2, exportSchema = false)
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
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
 }



