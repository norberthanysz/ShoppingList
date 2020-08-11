package com.example.shoppinglistapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.viewholders.ShoppingListItemsViewHolder
import com.example.shoppinglistapp.addnewlist.AddNewListViewModel

class ShoppingListItemsAdapter(
    private val shoppingListItems: List<String>,
    private val viewModel: AddNewListViewModel
) : RecyclerView.Adapter<ShoppingListItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemsViewHolder {
        return ShoppingListItemsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shoppingListItems.size
    }

    override fun onBindViewHolder(holder: ShoppingListItemsViewHolder, position: Int) {
        holder.item.text = holder.item.context.getString(R.string.dot) + shoppingListItems[position]
        holder.delete.setOnClickListener {
            viewModel.deleteItem(position)
        }
    }
}