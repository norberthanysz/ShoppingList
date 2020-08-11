package com.example.shoppinglistapp.archivedLists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistapp.MainSharedViewModel
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.adapters.ShoppingListAdapter
import com.example.shoppinglistapp.databinding.ArchivedListsFragmentBinding
import com.example.shoppinglistapp.models.UIState
import kotlinx.android.synthetic.main.archived_lists_fragment.*

class ArchivedListFragment : Fragment() {

    private lateinit var binding: ArchivedListsFragmentBinding
    private lateinit var viewModel: MainSharedViewModel

    lateinit var layoutManager: LinearLayoutManager
    lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.archived_lists_fragment, container, false
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

            if (uiState !is UIState.NavigateTo) {
                viewModel.previousState = uiState
            }

            when (uiState) {
                is UIState.Initialized -> {
                    if (viewModel.getActiveLists().isEmpty()) {
                        noArchivedListsText.visibility = View.VISIBLE
                        archivedListRecyclerView.visibility = View.GONE
                    } else {
                        noArchivedListsText.visibility = View.GONE
                        archivedListRecyclerView.visibility = View.VISIBLE
                        initRecyclerView()
                    }
                }
                is UIState.NavigateTo -> {
                    when (uiState.key) {
                        "GoBack" -> findNavController().navigateUp()
                        "details" -> {
                            val bundle = bundleOf("shoppingListId" to viewModel.shoppingListModelDetails.id)
                            findNavController().navigate(R.id.action_archivedListFragment_to_detailsFragment, bundle)
                        }

                    }
                }
            }
        })

        viewModel.uiState.postValue(UIState.Initialized)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        archivedListRecyclerView.layoutManager = layoutManager
        shoppingListAdapter = ShoppingListAdapter(viewModel.getArchivedLists(), viewModel)
        archivedListRecyclerView.adapter = shoppingListAdapter
    }


}