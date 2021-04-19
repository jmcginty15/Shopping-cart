package com.example.shoppingcart3.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart3.data.database.StoreDatabase
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.data.entities.OrderItemRelation
import com.example.shoppingcart3.data.entities.OrderWithItems
import com.example.shoppingcart3.data.repositories.ItemRepository
import com.example.shoppingcart3.data.repositories.OrderRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private var itemRepository: ItemRepository = ItemRepository(application)
    private var orderRepository: OrderRepository = OrderRepository(application)
    private var orderItemList = listOf<Item>()
    lateinit var displayedOrder: OrderWithItems

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

    private val _cartList = MutableLiveData<ArrayList<Item>>(arrayListOf())

    val orderList: LiveData<List<OrderWithItems>>
        get() = _orderList

    private val _orderList: MutableLiveData<List<OrderWithItems>> by lazy {
        MutableLiveData<List<OrderWithItems>>()
    }

    var currentTotalPrice = 0.0
    var currentTax = 0.0
    var currentGrandTotal = 0.0

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

    private fun onOrderSuccess(orders: List<OrderWithItems>) {
        _orderList.value = orders
    }

    private fun onOrderError(e: Throwable) {

    }

    fun addToCart(item: Item) {
        currentTotalPrice += item.price
        currentTax = currentTotalPrice * 0.075
        currentGrandTotal = currentTotalPrice + currentTax

        val list = _cartList.value
        list?.add(item)
        _cartList.value = list!!
    }

    fun removeFromCart(item: Item, position: Int) {
        currentTotalPrice -= item.price
        currentTax = currentTotalPrice * 0.075
        currentGrandTotal = currentTotalPrice + currentTax

        val list = _cartList.value
        list?.removeAt(position)
        _cartList.value = list!!
    }

    fun clearCart() {
        currentTotalPrice = 0.0
        currentTax = 0.0
        currentGrandTotal = 0.0
        _cartList.value = arrayListOf()
    }

    fun addOrder(order: Order, items: List<Item>) {
        orderItemList = items
        disposable.add(
            orderRepository.addOrder(order).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addOrderItems, this::onOrderError)
        )
    }

    private fun addOrderItems(orderId: Long) {
        val relationList = arrayListOf<OrderItemRelation>()

        for (item in orderItemList) {
            relationList.add(OrderItemRelation(item.itemId, orderId))
        }

        disposable.add(
            orderRepository.addItems(relationList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAddOrderSuccess, this::onOrderError)
        )
    }

    private fun onAddOrderSuccess() {
        getAllOrders()
    }

    fun displayOrder(order: OrderWithItems) {
        displayedOrder = order
    }
}