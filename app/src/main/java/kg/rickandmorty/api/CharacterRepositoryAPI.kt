package kg.rickandmorty.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kg.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import kg.rickandmorty.utils.Constant.PAGE_SIZE
import javax.inject.Inject

class CharacterRepositoryAPI @Inject constructor(private val serviceAPI: ServiceAPI) {

    fun getCharacter() : Flow<PagingData<Character>>{
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = PAGE_SIZE
                ),
            pagingSourceFactory = { PagingSourceAPI(serviceAPI) }
        ).flow
    }
}