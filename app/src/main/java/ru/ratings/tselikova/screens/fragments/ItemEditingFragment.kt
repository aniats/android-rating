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
import androidx.navigation.fragment.navArgs
import ru.ratings.tselikova.R
import ru.ratings.tselikova.databinding.ItemEditingBinding
import ru.ratings.tselikova.model.RatingItem
import ru.ratings.tselikova.screens.factory
import ru.ratings.tselikova.screens.viewmodels.ItemEditingViewModel

class ItemEditingFragment: Fragment() {

    private lateinit var binding: ItemEditingBinding
    private var itemId = -1
    private lateinit var listname: String
    private lateinit var prevName: String
    private var prevRating = 0
    private var listId = -1
    private val viewModel: ItemEditingViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val props by navArgs<ItemEditingFragmentArgs>()
        itemId = props.itemId
        listname = props.listname
        prevName = props.prevName
        prevRating = props.prevRating
        listId = props.listId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemEditingBinding.inflate(layoutInflater, container, false)

        binding.itemName.setText(prevName, TextView.BufferType.EDITABLE)
        binding.itemRating.setText(prevRating.toString(), TextView.BufferType.EDITABLE)

        binding.submitChanges.setOnClickListener {
            val newName = binding.itemName.text.toString()
            val newRating = binding.itemRating.text.toString()

            if (newName.isBlank()) {
                Toast.makeText(requireContext(), requireContext().getString(R.string.please_enter_itemname), Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            if (newRating.isBlank()) {
                Toast.makeText(requireContext(), requireContext().getString(R.string.please_enter_itemrating), Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            viewModel.updateElem(itemId, newName, newRating.toInt(), listname, listId)

            findNavController().navigate(ItemEditingFragmentDirections.actionItemEditingFragment2ToSpecificListFragment2(listname))
        }

        binding.deleteItem.setOnClickListener {
            viewModel.deleteItem(RatingItem(id = itemId, listId = listId, name = prevName, rating = prevRating))
            findNavController().navigate(ItemEditingFragmentDirections.actionItemEditingFragment2ToSpecificListFragment2(listname))
        }

        return binding.root
    }
}