package kg.rickandmorty.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.rickandmorty.database.CharacterDAO
import kg.rickandmorty.database.CharacterDatabase
import kg.rickandmorty.database.CharacterRepositoryDB
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): CharacterDatabase {
        return Room
            .databaseBuilder(app, CharacterDatabase::class.java, "character_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesCharacterDao(characterDatabase: CharacterDatabase): CharacterDAO{
        return characterDatabase.characterDao()
    }

    @Provides
    fun providesRepository(database: CharacterDatabase): CharacterRepositoryDB{
        return CharacterRepositoryDB(database)
    }
}