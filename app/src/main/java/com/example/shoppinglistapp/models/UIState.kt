package com.example.shoppinglistapp.models

import android.os.Bundle

sealed class UIState {
    object Initialized : UIState()
    object InProgress : UIState()
    object NotInProgress : UIState()
    object Refresh : UIState()
    data class NavigateTo(val key: String?, val bundle: Bundle? = null): UIState()
}