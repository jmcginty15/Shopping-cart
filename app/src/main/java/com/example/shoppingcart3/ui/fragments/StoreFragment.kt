package com.example.shoppingcart3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart3.data.entities.Item
import com.example.shoppingcart3.databinding.StoreFragmentBinding
import com.example.shoppingcart3.ui.MainViewModel
import com.example.shoppingcart3.ui.adapters.StoreAdapter


class StoreFragment : Fragment() {
    private lateinit var binding: StoreFragmentBinding
    private val mViewModel: MainViewModel by activityViewModels()
    private val storeAdapter: StoreAdapter by lazy {
        StoreAdapter(this::addToCart)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StoreFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.storeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storeAdapter
        }

        mViewModel.itemList.observe(viewLifecycleOwner, { items ->
            storeAdapter.addData(items)
        })
    }

    private fun addToCart(item: Item) {
        mViewModel.addToCart(item)
    }
}