package com.example.shoppinglistapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.viewholders.ShoppingListItemsViewHolder
import com.example.shoppinglistapp.addnewlist.AddNewListViewModel
import com.example.shoppinglistapp.details.DetailsViewModel
import com.example.shoppinglistapp.models.ShoppingListModel

class DetailsListItemsAdapter(
    private val shoppingList: ShoppingListModel,
    private val viewModel: DetailsViewModel
) : RecyclerView.Adapter<ShoppingListItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemsViewHolder {
        return ShoppingListItemsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shoppingList.items.size
    }

    override fun onBindViewHolder(holder: ShoppingListItemsViewHolder, position: Int) {
        holder.item.text = holder.item.context.getString(R.string.dot) + shoppingList.items[position]
        if (shoppingList.active) {
            holder.delete.visibility = View.VISIBLE
            holder.delete.setOnClickListener {
                viewModel.deleteItem(position)
            }
        } else {
            holder.delete.visibility = View.INVISIBLE

        }
    }
}