package kg.rickandmorty.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kg.rickandmorty.R
import kg.rickandmorty.databinding.FragmentDetailsCharacterBinding

class DetailsCharacter : Fragment() {

    private var _binding: FragmentDetailsCharacterBinding? = null
    private val binding: FragmentDetailsCharacterBinding
        get() = _binding!!
    private val args: DetailsCharacterArgs by navArgs()

    private var deadColor: Int = 0
    private var aliveColor: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsCharacterBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deadColor = ContextCompat.getColor(binding.root.context, R.color.dead)
        aliveColor = ContextCompat.getColor(binding.root.context, R.color.alive)

        Picasso.get().load(args.character.image).into(binding.imageCharacter)
        binding.name.text = args.character.name
        binding.status.text = args.character.status
        binding.species.text = args.character.species
        binding.type.text = args.character.type

        if(args.character.status.equals("Dead")){
            binding.card.strokeColor = deadColor
            binding.restOfCard.setBackgroundColor(deadColor)
        }else{
            binding.card.strokeColor = aliveColor
            binding.restOfCard.setBackgroundColor(aliveColor)
        }
    }
}
