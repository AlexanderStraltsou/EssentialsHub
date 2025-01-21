package com.example.essentialshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essentialshub.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: PackingListAdapter


    private val weekendTripItems = mutableListOf(
        PackingItem("Toothbrush"),
        PackingItem("Shampoo"),
        PackingItem("Clothes"),
        PackingItem("Shoes"),
        PackingItem("Sunscreen"),
        PackingItem("Towel"),
        PackingItem("Passport"),
        PackingItem("Charger"),
        PackingItem("Snacks"),
        PackingItem("Water Bottle")
    )

    private val sunnyWeekItems = mutableListOf(
        PackingItem("Sunglasses"),
        PackingItem("Hat"),
        PackingItem("Sunscreen"),
        PackingItem("Swimsuit"),
        PackingItem("Towel"),
        PackingItem("Flip Flops"),
        PackingItem("Camera"),
        PackingItem("Beach Bag")
    )

    private val workTripItems = mutableListOf(
        PackingItem("Laptop"),
        PackingItem("Charger"),
        PackingItem("Notepad"),
        PackingItem("Pens"),
        PackingItem("Business Cards"),
        PackingItem("Suit"),
        PackingItem("Dress Shoes"),
        PackingItem("Power Bank"),
        PackingItem("Headphones")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listType = arguments?.getString("listType") ?: "Packing List"
        binding.tvListTitle.text = listType

        // Select items based on listType
        val items = when (listType) {
            "Weekend Trip" -> weekendTripItems
            "Sunny Week" -> sunnyWeekItems
            "Work Trip" -> workTripItems
            else -> mutableListOf()
        }

        listAdapter = PackingListAdapter(items)
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvItems.adapter = listAdapter

        binding.btnAddItem.setOnClickListener {
            val newItemName = binding.etNewItem.text.toString().trim()
            if (newItemName.isNotEmpty()) {
                listAdapter.addItem(newItemName)
                binding.etNewItem.text?.clear()
            } else {

                binding.etNewItem.error = "Please enter an item name"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_LIST_TYPE = "listType"

        @JvmStatic
        fun newInstance(listType: String) = ListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_LIST_TYPE, listType)
            }
        }
    }
}