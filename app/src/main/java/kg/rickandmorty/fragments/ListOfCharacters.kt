package kg.rickandmorty.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kg.rickandmorty.R
import kg.rickandmorty.utils.adapter.ListAdapter
import kg.rickandmorty.utils.viewmodel.ListCharactersViewModel

class ListOfCharacters : Fragment(R.layout.fragment_list_of_characters) {

    private lateinit var listAdapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter()

    }
}