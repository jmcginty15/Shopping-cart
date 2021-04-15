package com.example.shoppingcart3.data.repositories

import com.example.shoppingcart3.data.dao.ItemDao
import com.example.shoppingcart3.data.entities.Item
import io.reactivex.Single

class ItemRepository(private val itemDao: ItemDao) {
    fun getAllItems(): Single<List<Item>> {
        return itemDao.getAllItems()
    }
}