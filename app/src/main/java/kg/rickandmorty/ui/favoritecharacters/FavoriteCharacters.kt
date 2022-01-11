package kg.rickandmorty.ui.favoritecharacters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kg.rickandmorty.R
import kg.rickandmorty.databinding.FragmentFavoriteCharactersBinding
import kg.rickandmorty.model.Character
import kg.rickandmorty.adapter.FavoriteAdapter

@AndroidEntryPoint
class FavoriteCharacters : Fragment(), FavoriteAdapter.onCLickListener{

    private var _binding: FragmentFavoriteCharactersBinding? = null
    private val binding: FragmentFavoriteCharactersBinding
        get() = _binding!!

    private lateinit var listAdapter: FavoriteAdapter
    private val viewModel: FavoriteCharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteCharactersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = FavoriteAdapter(this, emptyList())
        setupAdapter()
        collectData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun collectData(){
        viewModel.getFavoriteCharacters().observe(viewLifecycleOwner, Observer { response ->
            if(response.size >= 0){
                listAdapter.setData(response)
                listAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun deleteFavoriteCharacter(character: Character){
        viewModel.delete(character)
    }

    private fun setupAdapter(){
        binding.listOfCharacters.apply {
            adapter = listAdapter
        }
    }

    override fun onItemCLick(character: Character) {
        deleteFavoriteCharacter(character)
    }
}
