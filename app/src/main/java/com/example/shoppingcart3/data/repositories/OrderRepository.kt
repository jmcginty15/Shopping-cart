package com.example.shoppingcart3.data.repositories

import com.example.shoppingcart3.data.dao.OrderDao
import com.example.shoppingcart3.data.entities.Order
import io.reactivex.Completable
import io.reactivex.Single

class OrderRepository(private val orderDao: OrderDao) {
    fun getAllOrders(): Single<List<Order>> {
        return orderDao.getAllOrders()
    }

    fun addOrder(order: Order): Completable {
        return orderDao.addOrder(order)
    }
}