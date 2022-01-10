package kg.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.rickandmorty.R
import kg.rickandmorty.databinding.ItemListBinding
import kg.rickandmorty.ui.favoritecharacters.FavoriteCharactersDirections
import kg.rickandmorty.model.Character

class FavoriteAdapter(private val clickListener: onCLickListener,
                      private var result: List<Character>): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        if(itemCount > 0 ){
            holder.bind(result[position])
        }
    }

    override fun getItemCount(): Int {
        return result.size
    }

    fun setData(result: List<Character>){
        this.result = result
    }

    class FavoriteViewHolder(private val binding: ItemListBinding,
                             private val clickListener: onCLickListener): RecyclerView.ViewHolder(binding.root){

        private val deadColor: Int = ContextCompat.getColor(binding.root.context, R.color.dead)
        private val aliveColor: Int = ContextCompat.getColor(binding.root.context, R.color.alive)
        private val favoriteAdded: Int = ContextCompat.getColor(binding.root.context, R.color.favorite)

        fun bind(character: Character) {
            binding.name.text = character.name
            binding.status.text = character.status
            Picasso.get().load(character.image).into(binding.imageCharacter)
            if (character.status.equals("Dead")) {
                binding.card.strokeColor = deadColor
                binding.dataLL.setBackgroundColor(deadColor)
            } else {
                binding.card.strokeColor = aliveColor
                binding.dataLL.setBackgroundColor(aliveColor)
            }
            binding.card.setOnClickListener {
                val action =
                    FavoriteCharactersDirections.actionFavoriteCharactersToDetailsCharacter(character)
                it.findNavController().navigate(action)
            }

            binding.addFavorite.setColorFilter(favoriteAdded)

            binding.addFavorite.setOnClickListener {
                clickListener.onItemCLick(character)
            }
        }
    }

    interface onCLickListener{
        fun onItemCLick(character: Character)
    }
}