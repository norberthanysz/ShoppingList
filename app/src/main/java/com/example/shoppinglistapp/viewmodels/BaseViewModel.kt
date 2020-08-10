package com.example.shoppinglistapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.models.UIState

abstract class BaseViewModel : ViewModel() {

    var uiState: MutableLiveData<UIState> = MutableLiveData(UIState.Initialized)

}