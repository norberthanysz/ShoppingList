package com.example.shoppinglistapp.details

import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import com.example.shoppinglistapp.viewmodels.BaseViewModel
import io.realm.Realm
import io.realm.kotlin.where

class DetailsViewModel(
    private val listId: Int
) : BaseViewModel() {

    lateinit var realm: Realm
    var shoppingList = ShoppingListModel()

    fun getList() {
        val result = realm.where<ShoppingListModel>()
            .equalTo("id", listId)
            .findFirst()

        if (result != null) {
            shoppingList = result
        }
    }

    fun isListActive(): Boolean {
        return shoppingList.active
    }

    fun deleteItem(position: Int) {
        //todo delete item
        //todo refresh list after changed
    }

    fun addItem(item: String) {
        //todo add item
        //todo refresh list after changed
    }

    fun archiveList() {
        //todo Archive list
    }

}