package kg.rickandmorty.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.rickandmorty.model.Character
import kg.rickandmorty.utils.Constant.FIRST_PAGE
import kg.rickandmorty.utils.Constant.PAGE_SIZE
import java.lang.Exception

class PagingSourceAPI (private val serviceAPI: ServiceAPI) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex: Int = params.key ?: FIRST_PAGE

        return try{
            val response = serviceAPI.getCharacters(pageIndex)

            val nextKey =
                if(!response.results.isEmpty()){
                    pageIndex + (params.loadSize / PAGE_SIZE)
                }else{
                    null
                }

            LoadResult.Page(
                data = response.results,
                prevKey = if (pageIndex == FIRST_PAGE) null else pageIndex,
                nextKey = nextKey)
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }
}