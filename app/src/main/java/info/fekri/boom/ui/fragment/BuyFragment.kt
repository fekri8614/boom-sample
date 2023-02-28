package info.fekri.boom.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import info.fekri.boom.R
import info.fekri.boom.databinding.FragmentBuyBinding
import info.fekri.boom.ux.adapter.BuyAdapter
import info.fekri.boom.ux.adapter.BuyItemEvents
import info.fekri.boom.ux.adapter.ItemEvents
import info.fekri.boom.ux.data.Book
import info.fekri.boom.ux.data.BuyBookData

class BuyFragment : Fragment(), BuyItemEvents {
    private lateinit var binding: FragmentBuyBinding

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

        binding.swipeRefresh.setOnRefreshListener {

            Handler().postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 1500)

        }


        val data = arrayListOf<BuyBookData>(
            BuyBookData(
                "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg",
                "Nature is beautiful",
                "$12.9",
                "Fekri",
                "23.5.2"
            ),
            BuyBookData(
                "https://www.worldatlas.com/r/w768/upload/06/b0/a6/swiss-alps-edler-von-rabenstein.jpg",
                "Alps",
                "$29.2",
                "Fekri",
                "22.3.2"
            ),
            BuyBookData(
                "https://cdn.britannica.com/84/73184-004-E5A450B5/Sunflower-field-Fargo-North-Dakota.jpg",
                "Mind Flower",
                "$200",
                "Fekri",
                "18.23.2"
            )
        )
        val buyAdapter = BuyAdapter(data, this)
        binding.layoutBuy.recyclerMain.adapter = buyAdapter
        binding.layoutBuy.recyclerMain.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }

    override fun onBuyItemClicked(buyBookData: BuyBookData) {

        // lead user to the buy-part

    }

    override fun onBuyItemLongClicked(buyBookData: BuyBookData) {

        // buy the book

    }

}