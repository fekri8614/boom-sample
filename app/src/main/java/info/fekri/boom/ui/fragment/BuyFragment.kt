package info.fekri.boom.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.fekri.boom.R
import info.fekri.boom.databinding.FragmentBuyBinding
import info.fekri.boom.extra.BuyItemEvents
import info.fekri.boom.ux.adapter.BuyAdapter
import info.fekri.boom.ux.retrofit.ApiManager
import info.fekri.boom.ux.retrofit.models.BestBookKTData

class BuyFragment() : Fragment(), BuyItemEvents {
    private lateinit var binding: FragmentBuyBinding
    private val apiManager = ApiManager()

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
        initUi()
    }

    private fun initUi() {
        getBooksDataFromApi()
    }

    private fun getBooksDataFromApi() {

        apiManager.getBooksData(object :
            ApiManager.ApiCallback<List<BestBookKTData.Item.VolumeInfo>> {

            override fun onSuccess(data: List<BestBookKTData.Item.VolumeInfo>) {

                try {
                    // set recycler
                    setRecyclerView(data)

                } catch (e: Exception) {
                    Log.v("boomLog", e.message.toString())
                }

            }

            override fun onError(errMsg: String) {
                Log.v("boomLog", errMsg)
                binding.itemModuleMainBuy.txtSHowError.visibility = View.VISIBLE
            }
        })

    }

    private fun setRecyclerView(data: List<BestBookKTData.Item.VolumeInfo>) {

        try {

            val dataAdapter = ArrayList(data)

            val buyAdapter = BuyAdapter(dataAdapter, this)
            binding.itemModuleMainBuy.recyclerMain.adapter = buyAdapter
            binding.itemModuleMainBuy.recyclerMain.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        } catch (e: Exception) {
            Log.v("boomLog", e.message.toString())
        }


    }


    // ---------------------------------------------------------------------------

    override fun onBuyItemClicked(data: BestBookKTData.Item.VolumeInfo) {
        // do nothing
    }

    override fun onBuyItemLongClicked(data: BestBookKTData.Item.VolumeInfo) {
        // go to BuyBookActivity
    }

}