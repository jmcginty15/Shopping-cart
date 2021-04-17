package com.example.shoppingcart3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.data.entities.OrderWithItems
import io.reactivex.Single

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    fun getAllOrders(): Single<List<OrderWithItems>>

    @Insert
    fun addOrder(order: Order): Single<Long>
}