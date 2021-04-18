package com.example.shoppingcart3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart3.R
import com.example.shoppingcart3.databinding.OrderDetailsFragmentBinding
import com.example.shoppingcart3.ui.MainViewModel
import com.example.shoppingcart3.ui.adapters.OrderDetailsAdapter

class OrderDetailsFragment : Fragment() {
    private lateinit var binding: OrderDetailsFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()
    private val orderDetailsAdapter: OrderDetailsAdapter by lazy {
        OrderDetailsAdapter(mViewModel.displayedOrder)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrderDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderDetailsAdapter
        }

        binding.totalPrice.text = resources.getString(
            R.string.price,
            "%.2f".format(mViewModel.displayedOrder.order.totalPrice)
        )
        binding.tax.text = resources.getString(
            R.string.price,
            "%.2f".format(mViewModel.displayedOrder.order.tax)
        )
        binding.grandTotal.text = resources.getString(
            R.string.price,
            "%.2f".format(mViewModel.displayedOrder.order.grandTotal)
        )

        binding.backToCartButton.setOnClickListener { backToCart() }
        binding.backToOrdersButton.setOnClickListener { backToOrder() }
    }

    private fun backToCart() {
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.order_details_fragment_cart_pop)
    }

    private fun backToOrder() {
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.order_details_fragment_order_pop)
    }
}