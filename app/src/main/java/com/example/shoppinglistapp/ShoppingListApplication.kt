package com.example.shoppinglistapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class ShoppingListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("shopping_list.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}