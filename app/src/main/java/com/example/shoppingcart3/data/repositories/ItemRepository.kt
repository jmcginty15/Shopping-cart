package com.example.shoppingcart3.data.repositories

import android.app.Application
import com.example.shoppingcart3.data.dao.ItemDao
import com.example.shoppingcart3.data.database.StoreDatabase
import com.example.shoppingcart3.data.entities.Item
import io.reactivex.Completable
import io.reactivex.Single

class ItemRepository(private val application: Application) {
    private val db = StoreDatabase.getDatabase(application) // should be in repo

    fun getAllItems(): Single<List<Item>> {
        return db.itemDao().getAllItems()
    }

    fun addItem(item: Item): Completable {
        return db.itemDao().addItem(item)
    }
}