package com.example.shoppinglistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.models.UIState

class MainSharedViewModel : ViewModel() {

    var uiState: MutableLiveData<UIState> = MutableLiveData()

    fun getData() {
        //todo download data from DB
        uiState.postValue(UIState.NavigateTo("ListsView"))
    }

    fun addList() {
        //todo go to add list view
    }

    fun showArchivedLists() {
        //todo go to archived lists view
    }

}