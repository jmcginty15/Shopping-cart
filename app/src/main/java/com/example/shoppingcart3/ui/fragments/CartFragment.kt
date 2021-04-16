package com.example.shoppingcart3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart3.R
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.databinding.CartFragmentBinding
import com.example.shoppingcart3.ui.MainViewModel
import com.example.shoppingcart3.ui.adapters.CartAdapter

class CartFragment : Fragment() {
    private lateinit var binding: CartFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()
    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(this::removeFromCart)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CartFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        mViewModel.cartList.observe(viewLifecycleOwner, { items ->
            cartAdapter.addData(items)
        })

        mViewModel.currentGrandTotal.observe(viewLifecycleOwner, { grandTotal ->
            binding.totalPrice.text =
                resources.getString(R.string.price, "%.2f".format(mViewModel.currentTotalPrice))
            binding.tax.text =
                resources.getString(R.string.price, "%.2f".format(mViewModel.currentTax))
            binding.grandTotal.text =
                resources.getString(R.string.price, "%.2f".format(grandTotal))
        })
    }

    private fun removeFromCart(item: Item, position: Int) {
        mViewModel.removeFromCart(item, position)
    }
}