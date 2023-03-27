package info.fekri.boom.mainUi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerHomeBinding
import info.fekri.boom.extra.MoreUiEvents
import info.fekri.boom.model.data.MoreUiData

class MoreAdapter(private val data: ArrayList<MoreUiData>, private val itemEvents: MoreUiEvents) :
    RecyclerView.Adapter<MoreAdapter.HistoryViewHolder>() {
    private lateinit var binding: ItemRecyclerHomeBinding

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(itemBook: MoreUiData) {

            Glide
                .with(itemView.context)
                .load(itemBook.coverImageUrl)
                .error(R.drawable.broken_img)
                .into(binding.imgRecyclerHome)

            itemView.setOnClickListener {
                itemEvents.onMoreUiItemClicked(itemBook)
            }
            itemView.setOnLongClickListener {
                itemEvents.onMoreUiItemLongClicked(itemBook)
                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding.root)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

}