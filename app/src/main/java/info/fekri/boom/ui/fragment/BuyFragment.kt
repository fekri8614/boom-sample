package info.fekri.boom.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import info.fekri.boom.ux.retrofit.ApiManager
import info.fekri.boom.ux.retrofit.models.BuyBooksToUseData
import info.fekri.boom.ux.room.MyDatabase

class BuyFragment() : Fragment(), BuyItemEvents {
    private lateinit var binding: FragmentBuyBinding
    private val apiManager = ApiManager()
    private lateinit var dataNews: ArrayList<Pair<String, String>>


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
                getNewsFromApi()
                binding.swipeRefresh.isRefreshing = false
            }, 1500)
        }

        initUi()
    }

    private fun initUi() {
        initApi()
    }

    private fun initApi() {
        getNewsFromApi()
        getBooksDataFromApi()
    }

    private fun getBooksDataFromApi() {
        apiManager.getBooksDataList(object : ApiManager.ApiCallback<List<BuyBooksToUseData.Item>> {
            override fun onSuccess(data: List<BuyBooksToUseData.Item>) {
                initRecycler(data)
            }

            override fun onError(errMsg: String) {
                binding.layoutNews.txtNews.text = "Error: $errMsg"
            }
        })
    }

    private fun getNewsFromApi() {
        apiManager.getNews(object : ApiManager.ApiCallback<ArrayList<Pair<String, String>>> {
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
                dataNews = data
                refreshNews()
            }

            override fun onError(errMsg: String) {
                binding.layoutNews.txtNews.text = "Error: $errMsg"
            }
        })
    }

    private fun refreshNews() {

        try {

            val randomAccess = (1..10).random() // data size = 10

            // set the news text
            binding.layoutNews.txtNews.text = dataNews[randomAccess].first

            // open url when clicked on image
            binding.layoutNews.imgNews.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(dataNews[randomAccess].second)))
            }

            binding.layoutNews.itemCardModuleNews.setOnClickListener {
                refreshNews()
            }

        } catch (e: Exception) {
            Log.v("boomLog", e.message.toString())
        }

    }

    private fun initRecycler(data: List<BuyBooksToUseData.Item>) {

        val buyAdapter = BuyAdapter(ArrayList(data), this)

        binding.layoutBuy.recyclerMain.adapter = buyAdapter
        binding.layoutBuy.recyclerMain.layoutManager = LinearLayoutManager(context)

    }

    // send data to BuyBookActivity -->
    override fun onBuyItemLongClicked(buyBookData: BuyBooksToUseData.Item) {

        val intent = Intent(activity, BuyBookActivity::class.java)

        intent.putExtra(KEY_SEND_DATA_BOOK_BUY, buyBookData)

        startActivity(intent)

    }

    override fun onBuyItemClicked(buyBookData: BuyBooksToUseData.Item) {
        // do nothing
    }

}