package kg.rickandmorty.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters_table")
data class Character(
    @PrimaryKey
    val name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String,
    var isFavorite: Boolean = false
) : Parcelable
