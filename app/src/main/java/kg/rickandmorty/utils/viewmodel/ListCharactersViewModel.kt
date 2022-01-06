package kg.rickandmorty.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.rickandmorty.model.Character

class ListCharactersViewModel : ViewModel() {

    var listCharacters = MutableLiveData<ArrayList<Character>>()

}