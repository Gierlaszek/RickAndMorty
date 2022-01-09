package kg.rickandmorty.ui.characterslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.rickandmorty.api.CharacterRepositoryAPI
import kg.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(private val characterRepositoryAPI: CharacterRepositoryAPI) : ViewModel() {

    fun getCharacterList() : Flow<PagingData<Character>>{
        return characterRepositoryAPI.getCharacter().cachedIn(viewModelScope)
    }
}