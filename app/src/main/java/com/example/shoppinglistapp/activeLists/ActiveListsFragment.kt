package com.example.shoppinglistapp.activeLists

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistapp.MainSharedViewModel
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.ShoppingListAdapter
import com.example.shoppinglistapp.databinding.ActiveListsFragmentBinding
import com.example.shoppinglistapp.models.UIState
import kotlinx.android.synthetic.main.active_lists_fragment.*

class ActiveListsFragment : Fragment() {

    private lateinit var binding: ActiveListsFragmentBinding
    private lateinit var viewModel: MainSharedViewModel

    lateinit var layoutManager: LinearLayoutManager
    lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.active_lists_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        binding.view = this

        activity?.let {
            viewModel = ViewModelProvider(it).get(MainSharedViewModel::class.java)
            binding.viewModel = viewModel
        }

        viewModel.previousState?.let {
            viewModel.uiState.value = it
        } ?: run {
            viewModel.uiState.value = UIState.Initialized
        }

        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->

            if(uiState !is UIState.NavigateTo) {
                viewModel.previousState = uiState
            }

            when (uiState) {
                is UIState.Initialized -> {
                    if (viewModel.getActiveLists().isEmpty()) {
                        noActiveListsText.visibility = View.VISIBLE
                        activeListRecyclerView.visibility = View.GONE
                    } else {
                        noActiveListsText.visibility = View.GONE
                        activeListRecyclerView.visibility = View.VISIBLE
                        initRecyclerView()
                    }
                }
                is UIState.NavigateTo -> {
                    when (uiState.key) {
                        "AddNewList" -> findNavController().navigate(R.id.action_activeListsFragment_to_addNewListFragment)
                        "ArchivedListsView" -> findNavController().navigate(R.id.action_activeListsFragment_to_archivedListFragment)
                    }
                }
            }
        })

        viewModel.uiState.postValue(UIState.Initialized)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        activeListRecyclerView.layoutManager = layoutManager
        shoppingListAdapter = ShoppingListAdapter(viewModel.getActiveLists())
        activeListRecyclerView.adapter = shoppingListAdapter
    }

}