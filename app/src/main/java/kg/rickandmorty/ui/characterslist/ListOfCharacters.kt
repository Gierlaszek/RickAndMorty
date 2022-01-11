package kg.rickandmorty.ui.characterslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kg.rickandmorty.databinding.FragmentListOfCharactersBinding
import kg.rickandmorty.model.Character
import kg.rickandmorty.adapter.ListAdapter
import kg.rickandmorty.ui.favoritecharacters.FavoriteCharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfCharacters: Fragment(), ListAdapter.OnCLickListener{

    private var _binding: FragmentListOfCharactersBinding? = null
    private lateinit var listAdapter: ListAdapter
    private val binding: FragmentListOfCharactersBinding
        get() = _binding!!
    private val viewModel: ListCharactersViewModel by viewModels()

    private val favoriteListViewModel: FavoriteCharactersViewModel by viewModels()

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
        listAdapter = ListAdapter(this, favoriteListViewModel.getFavoriteCharacters().value)
        setupAdapter()
        collectFavoriteData()
        collectData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun collectFavoriteData(){
        favoriteListViewModel.getFavoriteCharacters().observe(viewLifecycleOwner, { response ->
            if(response.size >= 0){
                listAdapter.setData(response)
                listAdapter.notifyDataSetChanged()
            }
        })
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

    override fun onItemCLick(character: Character) {
        if(character.isFavorite){
            favoriteListViewModel.delete(character)
            character.isFavorite = false
        }else{
            favoriteListViewModel.insert(character)
            character.isFavorite = true
        }
    }
}
