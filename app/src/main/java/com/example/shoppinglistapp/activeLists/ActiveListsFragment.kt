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
import com.example.shoppinglistapp.MainSharedViewModel
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.databinding.ActiveListsFragmentBinding
import com.example.shoppinglistapp.models.UIState
import kotlinx.android.synthetic.main.active_lists_fragment.*

class ActiveListsFragment : Fragment() {

    private lateinit var binding: ActiveListsFragmentBinding
    private lateinit var viewModel: MainSharedViewModel


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
        }

        viewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            when (uiState) {
                is UIState.Initialized -> {
                    viewModel.getActiveLists()
                    viewModel.getArchivedLists()
                    if (viewModel.getActiveLists().isEmpty()) {
                        noActiveListsText.visibility = View.VISIBLE
                        activeLists.visibility = View.GONE
                    } else {
                        noActiveListsText.visibility = View.GONE
                        activeLists.visibility = View.VISIBLE
                    }
                }
                is UIState.NavigateTo -> {
                    when (uiState.key) {
                        "AddNewList" -> {
                            //todo open new list view
                        }
                        "ArchivedListsView" -> {
                            //todo open archived lists view
                        }

                    }
                }
            }
        })

        viewModel.uiState.postValue(UIState.Initialized)
    }

}