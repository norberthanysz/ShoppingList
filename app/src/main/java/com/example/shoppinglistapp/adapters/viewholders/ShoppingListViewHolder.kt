package com.example.shoppinglistapp.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.listTitle
    val data: TextView = view.listData
    val count: TextView = view.itemsCountText
    val item: ConstraintLayout = view.shoppingList
}