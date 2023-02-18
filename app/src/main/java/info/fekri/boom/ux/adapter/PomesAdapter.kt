package info.fekri.boom.ux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerHomeBinding
import info.fekri.boom.ux.data.Book

class PomesAdapter(private val data: ArrayList<Book>, private val itemEvents: ItemEvents):
    RecyclerView.Adapter<PomesAdapter.PoemsViewHolder>() {
    private lateinit var binding: ItemRecyclerHomeBinding

    inner class PoemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemBook: Book) {

            binding.titleRecyclerHome.text = itemBook.title
            binding.descRecyclerHome.text = itemBook.desc
            Glide
                .with(itemView.context)
                .load(itemBook.coverImageUrl)
                .error(R.drawable.broken_img)
                .into(binding.imgRecyclerHome)

            itemView.setOnClickListener {
                itemEvents.onItemClicked(itemBook)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsViewHolder {
        binding = ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoemsViewHolder(binding.root)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PoemsViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

}