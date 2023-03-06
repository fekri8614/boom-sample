package info.fekri.boom.ux.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerHeroBinding
import info.fekri.boom.extra.HeroesEvent
import info.fekri.boom.ux.retrofit.models.HeroesData

@SuppressLint("SetTextI18n")
class HeroesAdapter(
    private val data: ArrayList<HeroesData.HeroesDataItem>,
    private val heroesEvent: HeroesEvent
) :
    RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {
    private lateinit var binding: ItemRecyclerHeroBinding

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* bind views here (set data to the items) */
        fun bindViews(data: HeroesData.HeroesDataItem) {

            binding.itemTxtNameHeroRecyclerHero.text = data.name
            binding.itemTxtFullNameRecyclerHero.text = "f-name: ${data.biography.fullName}"
            binding.itemTxtPowerRecyclerHero.text = "power: ${data.powerstats.power}"
            binding.itemTxtAlignmentRecyclerHero.text = "alignment: ${data.biography.alignment}"

            Glide
                .with(itemView)
                .load(data.images.md)
                .error(R.drawable.broken_img)
                .into(binding.itemImgCoverRecyclerHero)

            // events -->
            itemView.setOnClickListener {
                heroesEvent.onHeroesItemClicked(data)
            }
            itemView.setOnLongClickListener {
                heroesEvent.onHeroesItemLongClicked(data)
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerHeroBinding.inflate(inflater, parent, false)
        return HeroesViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bindViews(data[position])
    }
    override fun getItemCount(): Int = data.size

}