package com.example.shoppingcart3.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart3.data.database.StoreDatabase
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.data.repositories.ItemRepository
import com.example.shoppingcart3.data.repositories.OrderRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = StoreDatabase.getDatabase(application)
    private val disposable = CompositeDisposable()
    private var itemRepository: ItemRepository = ItemRepository(db.itemDao())
    private var orderRepository: OrderRepository = OrderRepository(db.orderDao())

    init {
        getAllItems()
        getAllOrders()
    }

    val itemList: LiveData<List<Item>>
        get() = _itemList

    private val _itemList: MutableLiveData<List<Item>> by lazy {
        MutableLiveData<List<Item>>()
    }

    val cartList: LiveData<ArrayList<Item>>
        get() = _cartList

    private val _cartList: MutableLiveData<ArrayList<Item>> by lazy {
        MutableLiveData<ArrayList<Item>>()
    }

    val orderList: LiveData<List<Order>>
        get() = _orderList

    private val _orderList: MutableLiveData<List<Order>> by lazy {
        MutableLiveData<List<Order>>()
    }

    var currentTotalPrice = 0.0
    var currentTax = 0.0
    var currentGrandTotal = MutableLiveData(0.0)

    private fun getAllItems() {
        disposable.add(
            itemRepository.getAllItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onItemSuccess, this::onItemError)
        )
    }

    private fun addItem(item: Item) {
        itemRepository.addItem(item)
    }

    private fun getAllOrders() {
        disposable.add(
            orderRepository.getAllOrders().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onOrderSuccess, this::onOrderError)
        )
    }

    private fun onItemSuccess(items: List<Item>) {
        _itemList.value = items
    }

    private fun onItemError(e: Throwable) {

    }

    private fun onOrderSuccess(orders: List<Order>) {
        _orderList.value = orders
    }

    private fun onOrderError(e: Throwable) {

    }

    fun addToCart(item: Item) {
        var list = _cartList.value
        if (list == null) {
            list = arrayListOf(item)
        } else {
            list.add(item)
        }
        _cartList.value = list

        currentTotalPrice += item.price
        currentTax = currentTotalPrice * 0.075
        currentGrandTotal.value = currentTotalPrice + currentTax
    }

    fun removeFromCart(item: Item, position: Int) {
        var list = _cartList.value
        list?.removeAt(position)
        _cartList.value = list

        currentTotalPrice -= item.price
        currentTax = currentTotalPrice * 0.075
        currentGrandTotal.value = currentTotalPrice + currentTax
    }
}