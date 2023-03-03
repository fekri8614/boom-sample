package info.fekri.boom.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.fekri.boom.databinding.FragmentBuyBinding
import info.fekri.boom.extra.KEY_SEND_DATA_BOOK_BUY
import info.fekri.boom.ui.activity.BuyBookActivity
import info.fekri.boom.ux.adapter.BuyAdapter
import info.fekri.boom.ux.adapter.BuyItemEvents
import info.fekri.boom.ux.data.BuyBookData
import info.fekri.boom.ux.room.MyDatabase

class BuyFragment(mContext: Context) : Fragment(), BuyItemEvents {
    private lateinit var binding: FragmentBuyBinding
    private val buyBookDao = MyDatabase.getDatabase(mContext).buyBookDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // swipe-refresh monitoring -->
        binding.swipeRefresh.setOnRefreshListener {
            Handler().postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 1500)
        }

        val data = listOf<BuyBookData>(
            BuyBookData(
                urlPic = "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg",
                nameBook = "Nature is beautiful",
                priceBook = "$12.9",
                writerBook = "Fekri",
                publishedData = "23.5.2",
                urlsBuy = "",
                urlPdf = ""
            ),
            BuyBookData(
                urlPic = "https://www.worldatlas.com/r/w768/upload/06/b0/a6/swiss-alps-edler-von-rabenstein.jpg",
                nameBook ="Alps",
                priceBook ="$29.2",
                writerBook = "Fekri",
                publishedData = "22.3.2",
                urlsBuy = "",
                urlPdf = ""
            ),
            BuyBookData(
                urlPic = "https://cdn.britannica.com/84/73184-004-E5A450B5/Sunflower-field-Fargo-North-Dakota.jpg",
                nameBook ="Mind Flower",
                priceBook = "$200",
                writerBook = "Fekri",
                publishedData = "18.23.2",
                urlsBuy = "",
                urlPdf = ""
            )
        )
        buyBookDao.insertAllBuy(data)

        val buyAdapter = BuyAdapter(ArrayList(buyBookDao.getAllBuyBooks()), this)
        binding.layoutBuy.recyclerMain.adapter = buyAdapter
        binding.layoutBuy.recyclerMain.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    // send data to BuyBookActivity -->
    override fun onBuyItemLongClicked(buyBookData: BuyBookData) {
        val intent = Intent(activity, BuyBookActivity::class.java)
        intent.putExtra(KEY_SEND_DATA_BOOK_BUY, buyBookData)
        startActivity(intent)
    }
    override fun onBuyItemClicked(buyBookData: BuyBookData) {
        // do nothing
    }

}