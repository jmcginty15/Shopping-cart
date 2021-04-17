package com.example.shoppingcart3.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["itemId", "orderId"], tableName = "order_items")
data class OrderItemRelation(
    val itemId: Int,
    val orderId: Long
)
