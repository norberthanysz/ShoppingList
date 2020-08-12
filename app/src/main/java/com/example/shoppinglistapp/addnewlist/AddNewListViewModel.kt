package com.example.shoppinglistapp.addnewlist

import androidx.lifecycle.MutableLiveData
import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import com.example.shoppinglistapp.viewmodels.BaseViewModel
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.createObject

class AddNewListViewModel : BaseViewModel() {

    lateinit var realm: Realm
    var list = ShoppingListModel()
    var listTitle = MutableLiveData<String>()
    var listItem = MutableLiveData<String>()

    fun onBackPressed() {
        uiState.postValue(UIState.NavigateTo("goBack"))
    }

    fun saveList() {
        realm.executeTransaction { realm ->
            val shoppingList = realm.createObject<ShoppingListModel>()
            val number: Number? = realm.where(ShoppingListModel::class.java).max("id")
            shoppingList.id = number!!.toInt() + 1
            shoppingList.title = listTitle.value.toString()
            shoppingList.active = true
            shoppingList.items = list.items
        }
        uiState.postValue(UIState.NavigateTo("goBack"))
    }

    fun addItem() {
        list.items.add(listItem.value.toString())
        uiState.postValue(UIState.Refresh)
    }

    fun onTitleChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        listTitle.value = charSequence.toString()
    }

    fun onItemChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        listItem.value = charSequence.toString()
    }

    fun deleteItem(position: Int) {
        list.items.removeAt(position)
        uiState.postValue(UIState.Refresh)
    }
}