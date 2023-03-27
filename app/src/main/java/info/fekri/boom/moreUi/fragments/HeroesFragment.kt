package info.fekri.boom.moreUi.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.fekri.boom.databinding.FragmentHeroesBinding
import info.fekri.boom.extra.HeroesEvent
import info.fekri.boom.extra.KEY_SEND_DATA_TO_SHOW
import info.fekri.boom.moreUi.ShowHeroesActivity
import info.fekri.boom.moreUi.HeroesAdapter
import info.fekri.boom.model.retrofit.ApiManager
import info.fekri.boom.model.data.HeroesData

class HeroesFragment() : Fragment(), HeroesEvent {
    private lateinit var binding: FragmentHeroesBinding
    private val apiManager = ApiManager()
    private lateinit var heroesAdapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // swipe-refresh monitoring and refreshing ui -->
        binding.swipeRefreshMain.setOnRefreshListener {
            initUi()
            Handler().postDelayed({
                binding.swipeRefreshMain.isRefreshing = false
            }, 1500)
        }

        initUi()
    }

    private fun initUi() {
        getHeroesDataFromApi()
    }

    private fun getHeroesDataFromApi() {

        apiManager.getHeroes(object : ApiManager.ApiCallback<ArrayList<HeroesData.HeroesDataItem>> {
            override fun onSuccess(data: ArrayList<HeroesData.HeroesDataItem>) {
                /*
                 If data is received send it to recyclerview to complete it.
                 */
                setRecyclerView(data)
            }

            override fun onError(errMsg: String) {
                Log.v("boomLog", errMsg)
                binding.itemModuleMainBuy.recyclerMain.visibility = View.GONE

                binding.itemModuleMainBuy.txtSHowError.text = errMsg
                binding.itemModuleMainBuy.layerShowError.visibility = View.VISIBLE
            }
        })

    }

    private fun setRecyclerView(data: ArrayList<HeroesData.HeroesDataItem>) {
        heroesAdapter = HeroesAdapter(data, this)
        binding.itemModuleMainBuy.recyclerMain.adapter = heroesAdapter
        binding.itemModuleMainBuy.recyclerMain.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    // events -->
    override fun onHeroesItemClicked(data: HeroesData.HeroesDataItem) {

    }

    override fun onHeroesItemLongClicked(data: HeroesData.HeroesDataItem) {

        val intent = Intent(activity, ShowHeroesActivity::class.java)
        intent.putExtra(KEY_SEND_DATA_TO_SHOW, data)
        startActivity(intent)

    }

}