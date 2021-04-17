package com.example.shoppingcart3.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithItems(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "itemId",
        associateBy = Junction(OrderItemRelation::class)
    )
    val items: List<Item>
)
