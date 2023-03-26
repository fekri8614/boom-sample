package info.fekri.boom.mainUi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerHomeBinding
import info.fekri.boom.extra.PoemsUiEvents
import info.fekri.boom.model.data.PoemsUiData

class PoemsAdapter(
    private val data: ArrayList<PoemsUiData>,
    private val itemEvents: PoemsUiEvents
) :
    RecyclerView.Adapter<PoemsAdapter.PoemsViewHolder>() {
    private lateinit var binding: ItemRecyclerHomeBinding

    inner class PoemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemBook: PoemsUiData) {

            Glide
                .with(itemView.context)
                .load(itemBook.coverImageUrl)
                .error(R.drawable.broken_img)
                .into(binding.imgRecyclerHome)

            itemView.setOnClickListener {
                itemEvents.onPoemsUiItemClicked(itemBook)
            }
            itemView.setOnLongClickListener {
                itemEvents.onPoemsUiItemLongClicked(itemBook)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsViewHolder {
        binding =
            ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoemsViewHolder(binding.root)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: PoemsViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

}