package info.fekri.boom.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import info.fekri.boom.R
import info.fekri.boom.databinding.DialogShowEventHomeBinding
import info.fekri.boom.databinding.FragmentHomeBinding
import info.fekri.boom.ux.adapter.BookRVAdapter
import info.fekri.boom.ux.data.BookRvModel

class HomeFragment() : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var mRequestQueue: RequestQueue
    private lateinit var poemsList: ArrayList<BookRvModel>
    private lateinit var imagineList: ArrayList<BookRvModel>
    private lateinit var loadingPB: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshHome.setOnRefreshListener {
            Handler().postDelayed({
                binding.swipeRefreshHome.isRefreshing = false
            }, 5000)

            bookDataUi()
        }
    }

    private fun bookDataUi() {
        // initialize variables
        loadingPB = binding.idLoadingProgressbarHome
        loadingPB.visibility = View.VISIBLE
        getBooksData("imaginational story for kids", "poems for kids")
    }

    private fun getBooksData(searchQuery1: String, searchQuery2: String) {

        poemsList = ArrayList()
        imagineList = ArrayList()

        mRequestQueue = Volley.newRequestQueue(requireContext())
        mRequestQueue.cache.clear()

        val imagineUrl = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery1"
        val poemsUrl = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery2"

        val queueImagine = Volley.newRequestQueue(requireContext())
        val queuePoems = Volley.newRequestQueue(requireContext())

        val requestImagine = JsonObjectRequest(Request.Method.GET, imagineUrl, null, { response ->
            loadingPB.visibility = View.GONE
            try {
                val itemsArray = response.getJSONArray("items")
                for (i in 0 until itemsArray.length()) {
                    val itemsObjImg = itemsArray.getJSONObject(i)
                    val volumeObjImg = itemsObjImg.getJSONObject("volumeInfo")
                    val titleImg = volumeObjImg.optString("title")
                    val subtitleImg = volumeObjImg.optString("subtitle")
                    val authorsArrayImg = volumeObjImg.getJSONArray("authors")
                    val publisherImg = volumeObjImg.optString("publisher")
                    val publishedDateImg = volumeObjImg.optString("publishedDate")
                    val descriptionImg = volumeObjImg.optString("description")
                    val pageCountImg = volumeObjImg.optInt("pageCount")
                    val imageLinksImg = volumeObjImg.optJSONObject("imageLinks")
                    val thumbnailImg = imageLinksImg.optString("thumbnail")
                    val previewLinkImg = volumeObjImg.optString("previewLink")
                    val infoLinkImg = volumeObjImg.optString("infoLink")
                    val saleInfoObjImg = itemsObjImg.optJSONObject("saleInfo")
                    val buyLinkImg = saleInfoObjImg.optString("buyLink")
                    val authorsArrayListImg: ArrayList<String> = ArrayList()
                    if (authorsArrayImg.length() != 0) {
                        for (j in 0 until authorsArrayImg.length()) {
                            authorsArrayListImg.add(authorsArrayImg.optString(i))
                        }
                    }

                    val imgInfo = BookRvModel(
                        titleImg,
                        subtitleImg,
                        authorsArrayListImg,
                        publisherImg,
                        publishedDateImg,
                        descriptionImg,
                        pageCountImg,
                        thumbnailImg,
                        previewLinkImg,
                        infoLinkImg,
                        buyLinkImg
                    )

                    imagineList.add(imgInfo)

                    val imgAdapter = BookRVAdapter(imagineList, requireContext())
                    val layoutManagerImg =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                    val imgRecyclerView = binding.recyclerImaginationHome
                    imgRecyclerView.layoutManager = layoutManagerImg
                    imgRecyclerView.adapter = imgAdapter

                }
            } catch (e: Exception) {
                Log.v("homeImagineLog", e.message.toString())
            }
        }, { error ->
            Toast.makeText(
                requireContext().applicationContext,
                "No book found, please try later",
                Toast.LENGTH_SHORT
            ).show()

            Log.v("homeImagineLog", error.message.toString())
        })

        val requestPoems = JsonObjectRequest(Request.Method.GET, poemsUrl, null, { response ->
            try {
                val itemsArray = response.getJSONArray("items")
                for (i in 0 until itemsArray.length()) {
                    val itemsObjPoem = itemsArray.getJSONObject(i)
                    val volumeObjPoem = itemsObjPoem.getJSONObject("volumeInfo")
                    val titlePoem = volumeObjPoem.optString("title")
                    val subtitlePoem = volumeObjPoem.optString("subtitle")
                    val authorsArrayPoem = volumeObjPoem.getJSONArray("authors")
                    val publisherPoem = volumeObjPoem.optString("publisher")
                    val publishedDatePoem = volumeObjPoem.optString("publishedDate")
                    val descriptionPoem = volumeObjPoem.optString("description")
                    val pageCountPoem = volumeObjPoem.optInt("pageCount")
                    val imageLinksPoem = volumeObjPoem.optJSONObject("imageLinks")
                    val thumbnailPoem = imageLinksPoem.optString("thumbnail")
                    val previewLinkPoem = volumeObjPoem.optString("previewLink")
                    val infoLinkPoem = volumeObjPoem.optString("infoLink")
                    val saleInfoObjPoem = itemsObjPoem.optJSONObject("saleInfo")
                    val buyLinkPoem = saleInfoObjPoem.optString("buyLink")
                    val authorsArrayListPoem: ArrayList<String> = ArrayList()
                    if (authorsArrayPoem.length() != 0) {
                        for (j in 0 until authorsArrayPoem.length()) {
                            authorsArrayListPoem.add(authorsArrayPoem.optString(i))
                        }
                    }

                    val poemsInfo = BookRvModel(
                        titlePoem,
                        subtitlePoem,
                        authorsArrayListPoem,
                        publisherPoem,
                        publishedDatePoem,
                        descriptionPoem,
                        pageCountPoem,
                        thumbnailPoem,
                        previewLinkPoem,
                        infoLinkPoem,
                        buyLinkPoem
                    )

                    poemsList.add(poemsInfo)

                    val poemAdapter = BookRVAdapter(poemsList, requireContext())
                    val poemLayoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                    val poemsRecycler = binding.recyclerPoemsHome
                    poemsRecycler.adapter = poemAdapter
                    poemsRecycler.layoutManager = poemLayoutManager
                }
            } catch (e: Exception) {
                Log.v("homePoemsLog", e.message.toString())
            }
        }, { error ->
            Toast.makeText(
                requireContext().applicationContext,
                "No book found, please try later",
                Toast.LENGTH_SHORT
            ).show()
            Log.v("homePoemLog", error.message.toString())
        })

        queueImagine.add(requestImagine)
        queuePoems.add(requestPoems)

    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    private fun initUi() {
        topIconsEvent() /* on icons click event */
    }

    private fun topIconsEvent() {

        binding.itemLibraryHome.setOnClickListener { replaceFragment(this) }

        binding.itemLibraryAudioHome.setOnClickListener { showDialog() }

        binding.itemLibraryWatchHome.setOnClickListener { showDialog() }

        binding.itemLibraryAllHome.setOnClickListener { showDialog() }

    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding = DialogShowEventHomeBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        Handler().postDelayed({ dialog.dismiss() }, 3000)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

}