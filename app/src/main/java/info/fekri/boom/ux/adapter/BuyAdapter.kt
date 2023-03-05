package info.fekri.boom.ux.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerBuyBinding
import info.fekri.boom.extra.BuyItemEvents
import info.fekri.boom.ux.retrofit.models.BestBookKTData

class BuyAdapter(
    private val data: ArrayList<BestBookKTData.Item.VolumeInfo>,
    private val buyItemEvents: BuyItemEvents
) :
    RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {
    private lateinit var binding: ItemRecyclerBuyBinding

    inner class BuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(data: BestBookKTData.Item.VolumeInfo) {

            try {
                binding.txtNameBookBuy.text = data.title
                binding.txtLangBookBuy.text = data.language
                binding.txtPublisherBookBuy.text = data.publisher
                binding.txtPublishedDateBookBuy.text = data.publishedDate

                Glide
                    .with(itemView)
                    .load(data.imageLinks.thumbnail)
                    .error(R.drawable.broken_img)
                    .into(binding.itemImgCoverBuy)

                // click-listener monitoring -->
                itemView.setOnLongClickListener {
                    buyItemEvents.onBuyItemLongClicked(data)
                    true
                }
                itemView.setOnClickListener {
                    buyItemEvents.onBuyItemClicked(data)
                }
            } catch (e: Exception) {
                Log.v("boomLog", e.message.toString())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerBuyBinding.inflate(inflater, parent, false)
        return BuyViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindViews(data[position])
    }
    override fun getItemCount(): Int = data.size
}