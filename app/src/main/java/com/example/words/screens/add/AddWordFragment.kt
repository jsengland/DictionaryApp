package com.example.words.screens.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.words.R
import com.example.words.databinding.FragmentAddWordBinding

class AddWordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentAddWordBinding.inflate(inflater)

        binding.lifecycleOwner = this.viewLifecycleOwner

        // Get the word object from the argument passed to it so that it can
        // be passed the view model via the view model factory.
        val word = AddWordFragmentArgs.fromBundle(arguments!!).wordDef

        val viewModelFactory = AddWordViewModelFactory(word, application)

        // The ViewModelProvider uses the factory to create the view model.
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(AddWordViewModel::class.java)

        // Navigates back to the search fragment
        binding.searchAgain.setOnClickListener {
            findNavController().navigate(R.id.action_addWordFragment_to_searchWordFragment)
        }

        // on the view model to add the word to the database.
        binding.addWord.setOnClickListener{
            AddWordViewModel(word, application).addWord(word)
            Toast.makeText(requireContext(), "Successfully added word to dictionary", Toast.LENGTH_LONG).show()
        }




        return binding.root
    }


}