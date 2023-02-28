package info.fekri.boom.ux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerBuyBinding
import info.fekri.boom.ux.data.BuyBookData

class BuyAdapter(private val data: ArrayList<BuyBookData>, private val buyItemEvents: BuyItemEvents) :
    RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {
    private lateinit var binding: ItemRecyclerBuyBinding

    inner class BuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemBookData: BuyBookData) {

            binding.txtNameBookBuy.text = itemBookData.nameBook
            binding.txtPriceBookBuy.text = itemBookData.priceBook
            binding.txtWriterBookBuy.text=  itemBookData.writerBook
            binding.txtPublishedDateBookBuy.text = itemBookData.publishedData

            Glide
                .with(itemView.context)
                .load(itemBookData.urlPic)
                .error(R.drawable.broken_img)
                .into(binding.itemImgCoverBuy)

            itemView.setOnLongClickListener {
                buyItemEvents.onBuyItemLongClicked(itemBookData)
                buyItemEvents.onBuyItemClicked(itemBookData)
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        binding = ItemRecyclerBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyViewHolder(binding.root)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

}