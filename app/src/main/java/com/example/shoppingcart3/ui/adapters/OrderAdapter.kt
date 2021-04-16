package com.example.shoppingcart3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart3.R
import com.example.shoppingcart3.data.entities.Order
import com.example.shoppingcart3.databinding.OrderItemBinding

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private var orderList: List<Order> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder =
        OrderViewHolder(
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) =
        with(holder) {
            val orderItem = orderList[position]
            loadData(orderItem)
        }

    override fun getItemCount(): Int = orderList.size

    class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun loadData(orderItem: Order) = with(binding) {
            itemCount.text = root.resources.getString(R.string.item_count, 7.toString())
            totalPrice.text =
                root.resources.getString(R.string.price, "%.2f".format(orderItem.grandTotal))
        }
    }

    fun addData(orders: List<Order>) {
        orderList = orders
        notifyDataSetChanged()
    }
}