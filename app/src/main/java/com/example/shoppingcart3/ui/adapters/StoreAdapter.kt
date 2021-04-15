package com.example.shoppingcart3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart3.R
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.databinding.StoreItemBinding

class StoreAdapter() : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    private var itemList: List<Item> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreViewHolder =
        StoreViewHolder(
            StoreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) = with(holder) {
        val storeItem = itemList[position]
        loadData(storeItem)
    }

    override fun getItemCount(): Int = itemList.size

    class StoreViewHolder(private val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun loadData(storeItem: Item) = with(binding) {
                itemName.text = storeItem.name
                itemPrice.text = root.resources.getString(R.string.price, storeItem.price)
                itemDescription.text = storeItem.description
            }
    }

    fun addData(items: List<Item>) {
        itemList = items
        notifyDataSetChanged()
    }
}