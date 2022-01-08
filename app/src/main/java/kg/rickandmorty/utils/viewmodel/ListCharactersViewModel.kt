package kg.rickandmorty.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.rickandmorty.api.CharacterRepository
import kg.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    fun getCharacterList() : Flow<PagingData<Character>>{
        return characterRepository.getCharacter().cachedIn(viewModelScope)
    }

}