package ru.ratings.tselikova.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.ratings.tselikova.databinding.SpecificListBinding
import ru.ratings.tselikova.screens.viewmodels.SpecificListViewModel
import ru.ratings.tselikova.screens.factory

class SpecificListFragment: Fragment() {

    private lateinit var binding: SpecificListBinding
    private lateinit var listname: String
    private var listId = -1
    private val viewModel: SpecificListViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val props by navArgs<SpecificListFragmentArgs>()
        listname = props.listname
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadList(listname)
        listId = viewModel.listId

        binding = SpecificListBinding.inflate(layoutInflater, container, false)

        viewModel.ratingItems.observe(viewLifecycleOwner, Observer {
            binding.noItemsYet.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val adapter = SimpleAdapter(context,
                it.map{ elem -> mapOf(
                    KEY_NAME to elem.name,
                    KEY_RATING to elem.rating,
                    KEY_ID to elem.id
                )},
                android.R.layout.simple_list_item_2,
                arrayOf(KEY_NAME, KEY_RATING),
                intArrayOf(android.R.id.text1, android.R.id.text2)
            )

            binding.listView.adapter = adapter

            binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val tmp = (adapter.getItem(position) as Map<*, *>)
                findNavController().navigate(SpecificListFragmentDirections.actionSpecificListFragment2ToItemEditingFragment2(listname, tmp[KEY_ID] as Int, tmp[KEY_NAME] as String, tmp[KEY_RATING] as Int, listId))
            }
        })

        binding.newElemButton.setOnClickListener {
            findNavController().navigate(SpecificListFragmentDirections.actionSpecificListFragment2ToItemEditingFragment2(listname, viewModel.requestNewId(), "", 0, listId))
        }

        binding.deleteListButton.setOnClickListener {
            viewModel.deleteList(listId)
            findNavController().navigate(SpecificListFragmentDirections.actionSpecificListFragment2ToListOfListsFragment2())
        }

        return binding.root
    }

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_RATING = "rating"
        private const val KEY_ID = "id"
    }
}