package ru.ratings.tselikova.screens.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.ratings.tselikova.databinding.ListOfListsBinding
import ru.ratings.tselikova.screens.factory
import ru.ratings.tselikova.screens.viewmodels.ListOfListsViewModel
import java.util.*

class ListOfListsFragment: Fragment() {

    private lateinit var binding: ListOfListsBinding
    private val viewModel: ListOfListsViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadLists()
        binding = ListOfListsBinding.inflate(layoutInflater, container, false)

        viewModel.ratings.observe(viewLifecycleOwner) { newList ->
            binding.noListsYet.visibility = if (newList.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.searchFilter.visibility = if (newList.isEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }

            binding.searchFilter.setOnEditorActionListener { textView: TextView, i: Int, keyEvent: KeyEvent? ->
                val filteringText = textView.text.toString()
                if (filteringText.isNotBlank()) {
                    val tmp = "Current filter: $filteringText"
                    binding.currentFilter.setText(tmp, TextView.BufferType.EDITABLE)
                    binding.currentFilter.visibility = View.VISIBLE
                } else {
                    binding.currentFilter.visibility = View.GONE
                }

                val innerAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    newList.filter {
                        it.lowercase(Locale.getDefault())
                            .contains(filteringText.lowercase()) || filteringText.isEmpty()
                    }
                )

                binding.allRatingLists.adapter = innerAdapter

                binding.allRatingLists.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        innerAdapter.getItem(position)?.let { it1 ->
                            findNavController().navigate(
                                ListOfListsFragmentDirections.actionListOfListsFragment2ToSpecificListFragment2(
                                    it1
                                )
                            )
                        }
                    }

                true
            }

            binding.clearFilter.setOnClickListener {
                binding.currentFilter.visibility = View.GONE
                binding.searchFilter.setText("", TextView.BufferType.EDITABLE)
                val innerAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    newList
                )

                binding.allRatingLists.adapter = innerAdapter

                binding.allRatingLists.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        innerAdapter.getItem(position)?.let { it1 ->
                            findNavController().navigate(
                                ListOfListsFragmentDirections.actionListOfListsFragment2ToSpecificListFragment2(
                                    it1
                                )
                            )
                        }
                    }
            }

            val firstAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                newList
            )

            binding.allRatingLists.adapter = firstAdapter

            binding.allRatingLists.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    firstAdapter.getItem(position)?.let { it1 ->
                        findNavController().navigate(
                            ListOfListsFragmentDirections.actionListOfListsFragment2ToSpecificListFragment2(
                                it1
                            )
                        )
                    }
                }
        }


        binding.newListButton.setOnClickListener {
            findNavController().navigate(ListOfListsFragmentDirections.actionListOfListsFragment2ToCreateNewListFragment2())
        }

        return binding.root
    }
}