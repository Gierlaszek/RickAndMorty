package kg.rickandmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String,
    var isFavorite: Boolean = false
) : Parcelable
