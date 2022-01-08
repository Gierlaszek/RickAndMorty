package kg.rickandmorty.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kg.rickandmorty.R
import kg.rickandmorty.databinding.FragmentDetailsCharacterBinding
import kg.rickandmorty.model.Character
import javax.inject.Inject

class DetailsCharacter (private val character: Character) : Fragment(R.layout.fragment_details_character) {

    private lateinit var binding: FragmentDetailsCharacterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsCharacterBinding.inflate(layoutInflater)

        Picasso.get().load(character.image).into(binding.imageCharacter)
        binding.name.text = character.name
        binding.status.text = character.status
        binding.species.text = character.species
        binding.type.text = character.type
    }
}