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
import com.example.shoppingcart3.databinding.OrderFragmentBinding
import com.example.shoppingcart3.ui.MainViewModel
import com.example.shoppingcart3.ui.adapters.OrderAdapter

class OrderFragment : Fragment() {
    private lateinit var binding: OrderFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()
    private val orderAdapter: OrderAdapter by lazy {
        OrderAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrderFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }

        mViewModel.orderList.observe(viewLifecycleOwner, { orders ->
            orderAdapter.addData(orders)
        })

        binding.backToCartButton.setOnClickListener { viewCart() }
    }

    private fun viewCart() {
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.order_fragment_pop)
    }
}