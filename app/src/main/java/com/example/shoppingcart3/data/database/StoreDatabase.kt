package com.example.shoppingcart3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingcart3.data.dao.ItemDao
import com.example.shoppingcart3.data.entities.Item

@Database(entities = [Item::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, StoreDatabase::class.java, "store_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}