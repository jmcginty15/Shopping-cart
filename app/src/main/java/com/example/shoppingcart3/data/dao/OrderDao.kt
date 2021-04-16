package com.example.shoppingcart3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingcart3.data.entities.Order
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    fun getAllOrders(): Single<List<Order>>

    @Insert
    fun addOrder(order: Order): Completable
}