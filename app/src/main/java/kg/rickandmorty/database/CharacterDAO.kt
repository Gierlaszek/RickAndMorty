package kg.rickandmorty.database

import androidx.room.*
import kg.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM characters_table")
    fun getAll(): Flow<List<Character>>

    @Delete
    fun deleteCharacter(character: Character)
}