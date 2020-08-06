package com.example.shoppinglistapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.viewholders.ShoppingListViewHolder
import com.example.shoppinglistapp.models.ShoppingListModel
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListAdapter(
    private val shoppingList: List<ShoppingListModel>
) : RecyclerView.Adapter<ShoppingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.title.text = shoppingList[position].title
        holder.data.text = getDate(shoppingList[position].createdAt)
    }

    private fun getDate(createdAt: Date?): String {
        return SimpleDateFormat("dd/MM/yyy").format(createdAt)
    }

}