package com.example.essentialshub
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.essentialshub.databinding.FragmentListBinding
import androidx.fragment.app.activityViewModels

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: PackingListAdapter
    private val packingListViewModel: PackingListViewModel by activityViewModels()

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

        val items = if (listType == "Custom List") {
            mutableListOf()
        } else {
            packingListViewModel.getItemsForListType(listType)
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