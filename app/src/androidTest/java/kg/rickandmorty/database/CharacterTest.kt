package kg.rickandmorty.database

import kg.rickandmorty.model.Character

object CharacterTest {
    fun createCharacter() = Character(
        name = "Test Name",
        status = "Alive",
        species = "Test Species",
        type = "Test Type",
        image = "https://rickandmortyapi.com/api/character/avatar/325.jpeg",
        isFavorite = true
    )
}