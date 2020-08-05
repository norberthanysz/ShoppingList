package com.example.shoppinglistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.models.UIState

class MainSharedViewModel : ViewModel() {

    var uiState: MutableLiveData<UIState> = MutableLiveData()

   

}