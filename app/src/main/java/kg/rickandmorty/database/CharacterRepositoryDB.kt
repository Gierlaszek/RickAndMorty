package kg.rickandmorty.database

import kg.rickandmorty.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryDB @Inject constructor(private val database: CharacterDatabase){

    suspend fun insert(character: Character) = withContext(Dispatchers.IO){
        database.characterDao().insertCharacter(character)
    }

    suspend fun delete(character: Character) = withContext(Dispatchers.IO){
        database.characterDao().deleteCharacter(character)
    }

    fun getList(): Flow<List<Character>>{
        return database.characterDao().getAll()
    }

}
