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
                setRecyclerView(data)
            }

            override fun onError(errMsg: String) {
                Log.v("boomLog", errMsg)
                binding.itemModuleMainBuy.txtSHowError.visibility = View.VISIBLE
            }
        })
    }

    private fun setRecyclerView(data: List<BestBookKTData.Item.VolumeInfo>) {

        val dataAdapter = ArrayList(data)

        val buyAdapter = BuyAdapter(dataAdapter, this)
        binding.itemModuleMainBuy.recyclerMain.adapter = buyAdapter
        binding.itemModuleMainBuy.recyclerMain.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }


    // ---------------------------------------------------------------------------

    override fun onBuyItemClicked(data: BestBookKTData.Item.VolumeInfo) {

    }

    override fun onBuyItemLongClicked(data: BestBookKTData.Item.VolumeInfo) {

    }

}