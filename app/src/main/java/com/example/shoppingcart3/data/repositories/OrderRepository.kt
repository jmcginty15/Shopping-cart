package com.example.shoppingcart3.data.repositories

import android.app.Application
import com.example.shoppingcart3.data.dao.OrderDao
import com.example.shoppingcart3.data.dao.OrderItemRelationDao
import com.example.shoppingcart3.data.database.StoreDatabase
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.data.entities.OrderItemRelation
import com.example.shoppingcart3.data.entities.OrderWithItems
import io.reactivex.Completable
import io.reactivex.Single

class OrderRepository(private val application: Application) {
    private val db = StoreDatabase.getDatabase(application) // should be in repo

    fun getAllOrders(): Single<List<OrderWithItems>> {
        return db.orderDao().getAllOrders()
    }

    fun addOrder(order: Order): Single<Long> {
        return db.orderDao().addOrder(order)
    }

    fun addItems(items: List<OrderItemRelation>): Completable {
        return db.relationDao().addRelation(items)
    }
}