package com.example.shoppingcart3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart3.R
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.databinding.CartItemBinding

class CartAdapter(private val listener: ((item: Item, position: Int) -> Unit)) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var itemList: List<Item> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder =
        CartViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        with(holder) {
            val cartItem = itemList[position]
            loadData(cartItem)
            removeFromCartButton.setOnClickListener { listener(cartItem, position) }
        }

    override fun getItemCount(): Int = itemList.size

    class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val removeFromCartButton = binding.removeFromCartButton

        fun loadData(cartItem: Item) = with(binding) {
            itemName.text = cartItem.name
            itemPrice.text =
                root.resources.getString(R.string.price, "%.2f".format(cartItem.price))
            itemDescription.text = cartItem.description
        }
    }

    fun addData(items: List<Item>) {
        itemList = items
        notifyDataSetChanged()
    }
}