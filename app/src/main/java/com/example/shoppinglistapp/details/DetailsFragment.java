package com.example.shoppinglistapp.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapp.R;
import com.example.shoppinglistapp.adapters.DetailsListItemsAdapter;

import io.realm.Realm;

public class DetailsFragment extends Fragment {

    private int listId;
    private DetailsViewModel viewModel;

    private TextView title;
    private RecyclerView itemsListRecyclerView;
    private ConstraintLayout activeListButtons;

    private ImageView backButton;
    private ImageView addButton;
    private Button archiveList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null)
            listId = getArguments().getInt("shoppingListId");

        viewModel = new DetailsViewModel(listId);
        viewModel.realm = Realm.getDefaultInstance();
        viewModel.getList();

        initViews();
        initButtons();
        setTitle();
        initRecyclerView();
    }

    private void initViews() {
        title = getView().findViewById(R.id.pageTitle);
        itemsListRecyclerView = getView().findViewById(R.id.itemsListRecyclerView);
        activeListButtons = getView().findViewById(R.id.activeListButtons);

        backButton = getView().findViewById(R.id.backButton);
        addButton = getView().findViewById(R.id.addButton);
        archiveList = getView().findViewById(R.id.archiveList);

        activeListButtons.setVisibility(viewModel.isListActive() ? View.VISIBLE : View.GONE);
    }

    private void initButtons() {
        backButton.setOnClickListener(view -> Navigation.findNavController(view).navigateUp());
        addButton.setOnClickListener(view -> {
            viewModel.archiveList();
        });
        archiveList.setOnClickListener(view -> {
            viewModel.archiveList();
            Navigation.findNavController(view).navigateUp();
        });
    }


    private void setTitle() {
        title.setText(viewModel.getShoppingList().getTitle());
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        itemsListRecyclerView.setLayoutManager(layoutManager);
        DetailsListItemsAdapter shoppingListItemsAdapter =
                new DetailsListItemsAdapter(viewModel.getShoppingList(), viewModel);
        itemsListRecyclerView.setAdapter(shoppingListItemsAdapter);
    }
}
