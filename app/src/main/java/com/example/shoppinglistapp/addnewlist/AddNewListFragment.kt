package com.example.shoppinglistapp.addnewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.ShoppingListItemsAdapter
import com.example.shoppinglistapp.databinding.AddNewListFragmentBinding
import com.example.shoppinglistapp.models.UIState
import io.realm.Realm
import kotlinx.android.synthetic.main.add_new_list_fragment.*

class AddNewListFragment : Fragment() {

    private lateinit var binding: AddNewListFragmentBinding
    private lateinit var viewModel: AddNewListViewModel

    lateinit var layoutManager: LinearLayoutManager
    lateinit var shoppingListItemsAdapter: ShoppingListItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.add_new_list_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        binding.view = this

        activity?.let {
            viewModel = ViewModelProvider(it).get(AddNewListViewModel::class.java)
            binding.viewModel = viewModel
        }

        viewModel.realm = Realm.getDefaultInstance()

        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            when (uiState) {
                is UIState.Initialized -> viewModel.list.items.clear()
                is UIState.Refresh -> showItems()
                is UIState.NavigateTo -> {
                    when (uiState.key) {
                        "goBack" -> {
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        })

        viewModel.uiState.postValue(UIState.Initialized)
    }

    private fun showItems() {
        layoutManager = LinearLayoutManager(context)
        itemsListRecyclerView.layoutManager = layoutManager
        shoppingListItemsAdapter = ShoppingListItemsAdapter(viewModel.list.items)
        itemsListRecyclerView.adapter = shoppingListItemsAdapter
    }
}