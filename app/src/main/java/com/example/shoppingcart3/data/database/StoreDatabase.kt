package com.example.shoppingcart3.data.database

import android.content.Context
import androidx.room.*
import com.example.shoppingcart3.data.dao.ItemDao
import com.example.shoppingcart3.data.dao.OrderDao
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.data.entities.Order

@Database(entities = [Item::class, Order::class], version = 2, exportSchema = true)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "store_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

// 'Shirt', 24.99, 'Wear one'
// 'Jeans', 30.73, 'Comfy and blue'
// 'XBox', 420.69, 'Nice'
// 'Bitcoin', 69420.69, 'Nice'
// 'Beer', 10.99, 'A six-pack of cold ones'
// 'Android phone', 9999.99, 'So you can run this beautiful app'
// 'Barbell', 799.99, 'Do some deadlifts and get yuuuuuuge'
// 'Baileys', 25.99, 'Creamy beige'