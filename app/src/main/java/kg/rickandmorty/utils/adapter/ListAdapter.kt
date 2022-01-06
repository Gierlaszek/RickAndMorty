package kg.rickandmorty.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import kg.rickandmorty.R
import kg.rickandmorty.model.Character
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.ArrayList

class ListAdapter : RecyclerView.Adapter<ListAdapter.CharacterViewHolder>(){

    private lateinit var listCharacters: ArrayList<Character>
    private var deadColor: Int = 0
    private var aliveColor: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        deadColor = ContextCompat.getColor(parent.context, R.color.dead)
        aliveColor = ContextCompat.getColor(parent.context, R.color.alive)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        Picasso.get().load(listCharacters[position].image).into(holder.image)
        holder.name.text = listCharacters[position].name
        holder.status.text = listCharacters[position].status
        if (listCharacters[position].status.equals("Dead")){
            holder.card.setStrokeColor(deadColor)
            holder.dataLL.setBackgroundColor(deadColor)
        }
        holder.addFavorite.setOnClickListener {
            if (listCharacters[position].isFavorite){
                //jesli jest dodany do ulubionych i klikniesz to usun z ulubionych
            }else{
                //dodaj do ulubionych jesli go nie ma
            }
        }
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: ArrayList<Character>){
        listCharacters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.imageCharacter
        var name: TextView = itemView.name
        var status: TextView = itemView.status
        var addFavorite: ImageView = itemView.addFavorite
        var card: MaterialCardView = itemView.card
        var dataLL: LinearLayout = itemView.dataLL
    }
}