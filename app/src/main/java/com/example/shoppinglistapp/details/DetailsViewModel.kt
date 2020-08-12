package com.example.shoppinglistapp.details

import com.example.shoppinglistapp.adapters.DetailsListInterface
import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import com.example.shoppinglistapp.viewmodels.BaseViewModel
import io.realm.Realm
import io.realm.RealmList
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
        realm.executeTransaction {
            val list = it.where<ShoppingListModel>().equalTo("id", listId).findFirst()
            list?.items?.removeAt(position)
        }
        callback.removeItem(position)
    }

    fun addItem(item: String) {
        var insertIndex: Int = 0
        realm.executeTransaction {
            val list = it.where<ShoppingListModel>().equalTo("id", listId).findFirst()
            list?.items?.add(item)
            insertIndex = list!!.items.size
        }
        callback.addItem(insertIndex)
    }

    fun changeStatus(active: Boolean) {
        realm.executeTransactionAsync {
            val list = it.where<ShoppingListModel>().equalTo("id", listId).findFirst()
            list?.active = active
        }
        callback.goBack()
    }

}