package kg.rickandmorty.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String,
    var isFavorite: Boolean
)
