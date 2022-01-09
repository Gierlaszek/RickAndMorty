package kg.rickandmorty.api

import kg.rickandmorty.model.CharacterListResponse
import kg.rickandmorty.utils.Constant.TYPE_DATA
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    @GET(TYPE_DATA)
    suspend fun getCharacters(@Query("page") query: Int) : CharacterListResponse
}