package com.example.shoppingcart3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingcart3.data.entities.Item
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Single<List<Item>>

    @Insert
    fun addItem(item: Item): Completable
}