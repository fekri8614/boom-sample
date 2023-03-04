package info.fekri.boom.ux.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerBuyBinding
import info.fekri.boom.ux.retrofit.models.BuyBooksToUseData

class BuyAdapter(private val data: ArrayList<BuyBooksToUseData.Item>, private val buyItemEvents: BuyItemEvents) :
    RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {
    private lateinit var binding: ItemRecyclerBuyBinding

    inner class BuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(dataBook: BuyBooksToUseData.Item) {

            try {

                binding.txtNameBookBuy.text          = dataBook.volumeInfo.title
                binding.txtLangBookBuy.text          = dataBook.volumeInfo.language
                binding.txtPublisherBookBuy.text     = dataBook.volumeInfo.publisher
                binding.txtPublishedDateBookBuy.text = dataBook.volumeInfo.publishedDate

                Glide
                    .with(itemView)
                    .load(dataBook.volumeInfo.imageLinks.thumbnail)
                    .error(R.drawable.broken_img)
                    .into(binding.itemImgCoverBuy)

                itemView.setOnLongClickListener {
                    buyItemEvents.onBuyItemLongClicked(dataBook)
                    true
                }
                itemView.setOnClickListener {
                    buyItemEvents.onBuyItemClicked(dataBook)
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