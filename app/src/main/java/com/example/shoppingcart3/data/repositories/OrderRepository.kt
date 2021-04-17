package com.example.shoppingcart3.data.repositories

import com.example.shoppingcart3.data.dao.OrderDao
import com.example.shoppingcart3.data.dao.OrderItemRelationDao
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.data.entities.OrderItemRelation
import com.example.shoppingcart3.data.entities.OrderWithItems
import io.reactivex.Completable
import io.reactivex.Single

class OrderRepository(private val orderDao: OrderDao, private val relationDao: OrderItemRelationDao) {
    fun getAllOrders(): Single<List<OrderWithItems>> {
        return orderDao.getAllOrders()
    }

    fun addOrder(order: Order): Single<Long> {
        return orderDao.addOrder(order)
    }

    fun addItems(items: List<OrderItemRelation>): Completable {
        return relationDao.addRelation(items)
    }
}