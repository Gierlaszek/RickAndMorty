package kg.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.rickandmorty.R
import kg.rickandmorty.databinding.ItemListBinding
import kg.rickandmorty.ui.characterslist.ListOfCharactersDirections
import kg.rickandmorty.model.Character

class ListAdapter(
    private val clickListener: OnCLickListener,
    private var favoriteList: List<Character>?
) : PagingDataAdapter<Character, ListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        for (favorite in favoriteList!!) {
            if (favorite.name == getItem(position)!!.name) {
                getItem(position)!!.isFavorite = true
            }
        }
        holder.bind(getItem(position)!!)
    }

    fun setData(result: List<Character>) {
        favoriteList = result
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    class CharacterViewHolder(
        private val binding: ItemListBinding,
        private val clickListener: OnCLickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val deadColor: Int = ContextCompat.getColor(binding.root.context, R.color.dead)
        private val aliveColor: Int = ContextCompat.getColor(binding.root.context, R.color.alive)
        private val favoriteAdded: Int = ContextCompat.getColor(binding.root.context, R.color.favorite)

        fun bind(character: Character) {
            binding.name.text = character.name
            binding.status.text = character.status
            Picasso.get().load(character.image).into(binding.imageCharacter)
            binding.card.strokeColor = if(character.status == "Dead") deadColor else aliveColor
            binding.textFrame.setBackgroundColor(if (character.status == "Dead") deadColor else aliveColor)
            binding.card.setOnClickListener {
                val action =
                    ListOfCharactersDirections.actionListOfCharactersToDetailsCharacter(character)
                it.findNavController().navigate(action)
            }

            binding.addFavorite.setColorFilter(if (character.isFavorite) favoriteAdded else 0)
            binding.addFavorite.setOnClickListener {
                clickListener.onItemCLick(character)
            }
        }
    }

    interface OnCLickListener {
        fun onItemCLick(character: Character)
    }
}
