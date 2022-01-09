package kg.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.rickandmorty.model.Character

@Database(entities = [Character::class], version= 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase(){
    abstract fun characterDao(): CharacterDAO
}