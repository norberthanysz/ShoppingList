package com.example.shoppinglistapp.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item.view.*
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item: TextView = view.itemName
    val delete: ImageView = view.deleteItem
}