package ru.ratings.tselikova.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.ratings.tselikova.R
import ru.ratings.tselikova.databinding.CreateNewListBinding
import ru.ratings.tselikova.screens.viewmodels.CreateNewListViewModel
import ru.ratings.tselikova.screens.factory

class CreateNewListFragment: Fragment() {

    private lateinit var binding: CreateNewListBinding
    private val viewModel: CreateNewListViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateNewListBinding.inflate(layoutInflater, container, false)

        binding.submitButton.setOnClickListener {
            val listName = binding.newListTitleInput.text.toString()
            if (listName.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.please_enter_listname),
                    Toast.LENGTH_SHORT
                ).show();
                return@setOnClickListener
            }

            viewModel.createNewList(listName)

            binding.newListTitleInput.setText("", TextView.BufferType.EDITABLE)

            findNavController().navigate(CreateNewListFragmentDirections.actionCreateNewListFragment2ToSpecificListFragment2(listName))
        }

        return binding.root
    }
}
