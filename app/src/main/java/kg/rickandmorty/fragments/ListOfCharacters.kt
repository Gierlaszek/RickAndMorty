package kg.rickandmorty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kg.rickandmorty.R
import kg.rickandmorty.databinding.FragmentListOfCharactersBinding
import kg.rickandmorty.utils.adapter.ListAdapter
import kg.rickandmorty.utils.viewmodel.ListCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfCharacters : Fragment(R.layout.fragment_list_of_characters) {

    private var _binding: FragmentListOfCharactersBinding? = null
    private lateinit var listAdapter: ListAdapter
    private val binding: FragmentListOfCharactersBinding
        get() = _binding!!
    private val viewModel: ListCharactersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfCharactersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter()
        setupAdapter()
        collectData()
    }

    private fun collectData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacterList().collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

    private fun setupAdapter(){
        binding.listOfCharactersRV.apply {
            adapter = listAdapter
        }
    }
}