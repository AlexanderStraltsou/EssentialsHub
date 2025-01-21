package com.example.essentialshub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essentialshub.databinding.ItemPackingListBinding

class PackingListAdapter(
    private val items: MutableList<PackingItem>
) : RecyclerView.Adapter<PackingListAdapter.PackingListViewHolder>() {

    inner class PackingListViewHolder(private val binding: ItemPackingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PackingItem) {
            binding.tvItemName.text = item.name
            binding.cbPacked.isChecked = item.isPacked
            binding.tvItemName.setTextColor(
                if (item.isSuperImportant) binding.root.context.getColor(R.color.super_important)
                else binding.root.context.getColor(android.R.color.black)
            )

            binding.cbPacked.setOnCheckedChangeListener { _, isChecked ->
                item.isPacked = isChecked
            }

            binding.btnDelete.setOnClickListener {
                removeItem(adapterPosition)
            }

            binding.btnMarkImportant.setOnClickListener {
                item.isSuperImportant = !item.isSuperImportant
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackingListViewHolder {
        val binding =
            ItemPackingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PackingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackingListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItem(newItem: String) {
        items.add(PackingItem(newItem))
        notifyItemInserted(items.size - 1)
    }

    private fun removeItem(position: Int) {
        if (position in items.indices) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}

data class PackingItem(
    val name: String,
    var isPacked: Boolean = false,
    var isSuperImportant: Boolean = false
)