package com.example.shoppinglistapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import com.example.shoppinglistapp.viewmodels.BaseViewModel
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

class MainSharedViewModel : BaseViewModel() {

    lateinit var realm: Realm
    var shoppingLists: MutableLiveData<List<ShoppingListModel>>? = null
    var previousState: UIState? = null
    var shoppingListModelDetails = ShoppingListModel()

    fun getData() {
        val result = realm.where<ShoppingListModel>()
            .sort("createdAt", Sort.DESCENDING)
            .findAll()

        shoppingLists?.value = result

        Log.d("result", result.toString())

        uiState.postValue(UIState.NavigateTo("ActiveListsView"))
    }

    fun getActiveLists(): List<ShoppingListModel> {
        return realm.where<ShoppingListModel>()
            .equalTo("active", true)
            .sort("createdAt", Sort.DESCENDING)
            .findAll()
    }

    fun getArchivedLists(): List<ShoppingListModel> {
        return realm.where<ShoppingListModel>()
            .equalTo("active", false)
            .sort("createdAt", Sort.DESCENDING)
            .findAll()
    }

    fun addList() {
        uiState.postValue(UIState.NavigateTo("AddNewList"))
    }

    fun showArchivedLists() {
        uiState.postValue(UIState.NavigateTo("ArchivedListsView"))
    }

    fun openActiveShoppingListDetails(position: Int) {
        shoppingListModelDetails = getActiveLists()[position]
        uiState.postValue(UIState.NavigateTo("details"))
    }


    fun openArchivedShoppingListDetails(position: Int) {
        shoppingListModelDetails = getArchivedLists()[position]
        uiState.postValue(UIState.NavigateTo("details"))
    }

    fun onBackPressed() {
        uiState.postValue(UIState.NavigateTo("GoBack"))
    }
}