package com.example.shoppingcart3.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart3.data.database.StoreDatabase
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.data.repositories.ItemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = StoreDatabase.getDatabase(application)
    private val disposable = CompositeDisposable()
    private var itemRepository: ItemRepository = ItemRepository(db.itemDao())

    init {
        getAllItems()
    }

    val itemList: LiveData<List<Item>>
        get() = _itemList

    private val _itemList: MutableLiveData<List<Item>> by lazy {
        MutableLiveData<List<Item>>()
    }


    private fun getAllItems() {
        disposable.add(
            itemRepository.getAllItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(items: List<Item>) {
        _itemList.value = items
    }

    private fun onError(e: Throwable) {

    }
}