package ru.ratings.tselikova.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.ratings.tselikova.R
import ru.ratings.tselikova.databinding.StartWindowBinding

class StartWindowFragment: Fragment() {

    private lateinit var binding: StartWindowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartWindowBinding.inflate(layoutInflater, container, false)

        binding.newListButton.setOnClickListener {
            findNavController().navigate(StartWindowFragmentDirections.actionStartWindowFragment2ToCreateNewListFragment2())
        }

        binding.viewListsButton.setOnClickListener {
            findNavController().navigate(StartWindowFragmentDirections.actionStartWindowFragment2ToListOfListsFragment2())
        }

        return binding.root
    }
}