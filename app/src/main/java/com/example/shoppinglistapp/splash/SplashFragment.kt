package com.example.shoppinglistapp.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoppinglistapp.MainSharedViewModel
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.databinding.SplashFragmentBinding
import com.example.shoppinglistapp.models.ShoppingListModel
import com.example.shoppinglistapp.models.UIState
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.Exception
import java.time.LocalDateTime

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

    @Suppress("DEPRECATION")
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
                        "ActiveListsView" -> findNavController().navigate(R.id.action_splashFragment_to_activeListsFragment)
                    }
                }
            }
        })

        Handler().postDelayed({
            viewModel.getData()
        }, splashScreenDelay)
    }

//    private fun addObjects() {
//        viewModel.realm.executeTransaction { realm ->
//            val shoppingList = realm.createObject<ShoppingListModel>()
//            val number: Number? = realm.where(ShoppingListModel::class.java).max("id")
//            shoppingList.id = number!!.toInt() + 1
//            shoppingList.title = "My List"
//            shoppingList.active = false
//            shoppingList.items = RealmList("asd", "asd")
//        }
//    }
}