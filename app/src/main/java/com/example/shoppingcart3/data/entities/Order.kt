package com.example.shoppingcart3.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(name = "total_price") val totalPrice: Double,
    @ColumnInfo(name = "tax") val tax: Double,
    @ColumnInfo(name = "grand_total") val grandTotal: Double
)