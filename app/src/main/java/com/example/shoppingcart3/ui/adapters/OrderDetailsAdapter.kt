package com.example.shoppingcart3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart3.R
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.data.entities.OrderWithItems
import com.example.shoppingcart3.databinding.OrderDetailsItemBinding

class OrderDetailsAdapter(order: OrderWithItems) :
    RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>() {
    private var itemList = order.items

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailsViewHolder =
        OrderDetailsViewHolder(
            OrderDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) = with(holder) {
        val orderItem = itemList[position]
        loadData(orderItem)
    }

    override fun getItemCount(): Int = itemList.size

    class OrderDetailsViewHolder(private val binding: OrderDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadData(orderItem: Item) = with(binding) {
            itemName.text = orderItem.name
            itemPrice.text =
                root.resources.getString(R.string.price, "%.2f".format(orderItem.price))
            itemDescription.text = orderItem.description
        }
    }
}