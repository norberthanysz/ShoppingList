package com.example.shoppinglistapp.details

import com.example.shoppinglistapp.adapters.DetailsListInterface
import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import com.example.shoppinglistapp.viewmodels.BaseViewModel
import io.realm.Realm
import io.realm.kotlin.where

class DetailsViewModel(
    private val listId: Int,
    private val callback: DetailsListInterface
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
        callback.refresh()
    }

    fun addItem(item: String) {
        //todo add item
        callback.refresh()
    }

    fun archiveList() {
        realm.executeTransactionAsync {
            val list = it.where<ShoppingListModel>().equalTo("id", listId).findFirst()
            list?.active = false
        }
        callback.goBack()
    }

}