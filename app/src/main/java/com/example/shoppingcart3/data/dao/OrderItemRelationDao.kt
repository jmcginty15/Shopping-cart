package com.example.shoppingcart3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.shoppingcart3.data.entities.OrderItemRelation
import io.reactivex.Completable

@Dao
interface OrderItemRelationDao {
    @Insert
    fun addRelation(relation: List<OrderItemRelation>): Completable
}