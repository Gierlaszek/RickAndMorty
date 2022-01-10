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

class ListAdapter(private val clickListener: onCLickListener, private var favoriteList: List<Character>?) : PagingDataAdapter<Character, ListAdapter.CharacterViewHolder>(CharacterDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener,
            favoriteList
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!, position)
    }


    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    fun setData(result: List<Character>){
        favoriteList = result
    }

    class CharacterViewHolder(private val binding: ItemListBinding,
                              private val clickListener: onCLickListener,
                              private var favoriteList: List<Character>? ): RecyclerView.ViewHolder(binding.root){

        private val deadColor: Int = ContextCompat.getColor(binding.root.context, R.color.dead)
        private val aliveColor: Int = ContextCompat.getColor(binding.root.context, R.color.alive)
        private val favoriteAdded: Int = ContextCompat.getColor(binding.root.context, R.color.favorite)

        fun bind(character: Character, position: Int){
            binding.name.text = character.name
            binding.status.text = character.status
            Picasso.get().load(character.image).into(binding.imageCharacter)
            if(character.status.equals("Dead")){
                binding.card.strokeColor = deadColor
                binding.dataLL.setBackgroundColor(deadColor)
            }else{
                binding.card.strokeColor = aliveColor
                binding.dataLL.setBackgroundColor(aliveColor)
            }
            binding.card.setOnClickListener {
                val action = ListOfCharactersDirections.actionListOfCharactersToDetailsCharacter(character)
                it.findNavController().navigate(action)
            }

            if(favoriteList != null){
                for(favorite in favoriteList!!){
                    if(favorite.name.equals(character.name)){
                        character.isFavorite = true
                        binding.addFavorite.setColorFilter(favoriteAdded)
                    }
                }
            }
            if(character.isFavorite == true){
                binding.addFavorite.setColorFilter(favoriteAdded)
            }else{
                binding.addFavorite.setColorFilter(0)
            }

            binding.addFavorite.setOnClickListener {
                clickListener.onItemCLick(character, position)
            }
        }
    }

    interface onCLickListener{
        fun onItemCLick(character: Character, position: Int)
    }
}