package com.example.shoppinglistapp.splash

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
import com.example.shoppinglistapp.databinding.SplashFragmentBinding
import com.example.shoppinglistapp.models.UIState

class SplashFragment : Fragment() {

    private val splashScreenDelay: Long = 500


    private lateinit var binding: SplashFragmentBinding
    private lateinit var viewModel: MainSharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.splash_fragment, container, false
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
                is UIState.NavigateTo -> {
                    when (uiState.key) {
                        "ListsView" -> {
                            //todo go to listsView
                        }
                    }
                }
            }
        })

        Handler().postDelayed(Runnable {
            viewModel.getData()
        }, splashScreenDelay)
    }
}