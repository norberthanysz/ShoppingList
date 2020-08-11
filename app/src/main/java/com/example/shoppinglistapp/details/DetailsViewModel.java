package com.example.shoppinglistapp.details;

import com.example.shoppinglistapp.viewmodels.BaseViewModel;

import io.realm.Realm;

public class DetailsViewModel extends BaseViewModel {

    private int shoppingListId;
    private Realm realm;

    public DetailsViewModel(int shoppingListId) {
        this.shoppingListId = shoppingListId;
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }


}
