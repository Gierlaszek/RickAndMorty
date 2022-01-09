package kg.rickandmorty.ui.favoritecharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.rickandmorty.database.CharacterRepositoryDB
import kg.rickandmorty.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharactersViewModel @Inject constructor(private val characterRepositoryDB: CharacterRepositoryDB): ViewModel() {

    fun getFavoriteCharacters(): LiveData<List<Character>>{
        return characterRepositoryDB.getList().flowOn(Dispatchers.Main).asLiveData(context = viewModelScope.coroutineContext)
    }

    fun insert(character: Character){
        viewModelScope.launch {
            characterRepositoryDB.insert(character)
        }
    }

    fun delete(character: Character){
        viewModelScope.launch {
            characterRepositoryDB.delete(character)
        }
    }

}