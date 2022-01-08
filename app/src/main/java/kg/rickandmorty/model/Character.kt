package kg.rickandmorty.model

data class Character(
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String,
    var isFavorite: Boolean = false
)
