package com.example.shoppingcart3.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `orders` (`orderId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `total_price` REAL NOT NULL, `tax` REAL NOT NULL, `grand_total` REAL NOT NULL)")
    }
}