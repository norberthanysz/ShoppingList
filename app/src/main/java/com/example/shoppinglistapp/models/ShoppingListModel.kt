package com.example.shoppinglistapp.models

import io.realm.RealmList
import io.realm.RealmObject
import java.util.*

open class ShoppingListModel(
    var id: Int = 0,
    var title: String = "",
    var createdAt: Date? = Date(),
    var active: Boolean = true,
    var items: RealmList<String> = RealmList()
) : RealmObject() {

}