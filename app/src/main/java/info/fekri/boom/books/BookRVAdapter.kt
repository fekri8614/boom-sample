package info.fekri.boom.books

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import info.fekri.boom.R
import info.fekri.boom.databinding.ItemRecyclerHomeBinding
import info.fekri.boom.model.data.BookRvModel

class BookRVAdapter(
    private var bookList: ArrayList<BookRvModel>,
    private var ctx: Context
) : RecyclerView.Adapter<BookRVAdapter.BookViewHolder>() {
    private lateinit var binding: ItemRecyclerHomeBinding

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookIV: AppCompatImageView = binding.imgRecyclerHome
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerHomeBinding.inflate(inflater, parent, false)
        return BookViewHolder(binding.root)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookInfo = bookList[position]

        Picasso
            .get()
            .load(bookInfo.thumbnail)
            .error(R.drawable.broken_img)
            .into(holder.bookIV)

        holder.itemView.setOnLongClickListener {

            // I can send all data by one Intent
            // I tried other way!
            val intent = Intent(ctx, BookDetailsActivity::class.java)
            intent.putExtra("title", bookInfo.title)
            intent.putExtra("subtitle", bookInfo.subtitle)
            intent.putExtra("authors", bookInfo.authors)
            intent.putExtra("publisher", bookInfo.publisher)
            intent.putExtra("publishedDate", bookInfo.publishedDate)
            intent.putExtra("description", bookInfo.description)
            intent.putExtra("pageCount", bookInfo.pageCount)
            intent.putExtra("thumbnail", bookInfo.thumbnail)
            intent.putExtra("previewLink", bookInfo.previewLink)
            intent.putExtra("infoLink", bookInfo.infoLink)
            intent.putExtra("buyLink", bookInfo.buyLink)
            intent.putExtra("pdfLink", bookInfo.pdfLink)

            ctx.startActivity(intent)

            true
        }

    }

}