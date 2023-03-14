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
import androidx.fragment.app.Fragment
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

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var loadingPB: ProgressBar

    private lateinit var imgRequestQueue: RequestQueue
    private lateinit var imgList: ArrayList<BookRvModel>

    private lateinit var sciRequestQueue: RequestQueue
    private lateinit var sciList: ArrayList<BookRvModel>

    companion object {
        const val BASE_URL_HOME = "https://www.googleapis.com/books/v1/volumes?q="
    }

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
        loadingPB = binding.progressLoadHome
        loadingPB.visibility = View.VISIBLE

        openPdfEvent()
    }
    override fun onStart() {
        super.onStart()
        initUi()
    }

    private fun initUi() {
        topIconsEvent() /* on icons click event */
        openPdfEvent()
    }
    private fun openPdfEvent() {
        imagineUi()
        scientificUi()
    }
    private fun imagineUi() {
        getImagineFromAPI("Imaginal story")
    }

    private fun getImagineFromAPI(query: String) {

        imgList = arrayListOf()

        imgRequestQueue = Volley.newRequestQueue(requireContext())

        imgRequestQueue.cache.clear()

        val urlImg = "$BASE_URL_HOME$query" // I don't mean ImageView! I mean Imagination

        val imgQueue = Volley.newRequestQueue(requireContext())

        val imgRequest = JsonObjectRequest(
            Request.Method.GET,
            urlImg,
            null,
            { response ->
                loadingPB.visibility = View.GONE

                try {
                    val itemsArray = response.getJSONArray("items")
                    for (i in 0 until itemsArray.length()) {
                        val itemsObj = itemsArray.getJSONObject(i)
                        val volumeObj = itemsObj.getJSONObject("volumeInfo")
                        val title = volumeObj.optString("title")
                        val subtitle = volumeObj.optString("subtitle")
                        val authorsArray = volumeObj.getJSONArray("authors")
                        val publisher = volumeObj.optString("publisher")
                        val publishedDate = volumeObj.optString("publishedDate")
                        val description = volumeObj.optString("description")
                        val pageNo = volumeObj.optInt("pageCount")
                        val imgLink = volumeObj.optJSONObject("imageLinks")
                        val thumbnail = imgLink.optString("thumbnail")
                        val previewLink = volumeObj.optString("previewLink")
                        val infoLink = volumeObj.optString("infoLink")
                        val saleInfoObj = volumeObj.optJSONObject("saleInfo")
                        val buyLink = saleInfoObj.optString("buyLink")
                        val authorsAL: ArrayList<String> = ArrayList()
                        val pdfLink = itemsObj.optJSONObject("accessInfo")?.optString("pdfLink")
                        if (authorsArray.length() != 0) {
                            for (j in 0 until authorsArray.length()) {
                                authorsAL.add(authorsArray.optString(i))
                            }
                        }

                        val imgInfo = BookRvModel(
                            title,
                            subtitle,
                            authorsAL,
                            publisher,
                            publishedDate,
                            description,
                            pageNo,
                            thumbnail,
                            previewLink,
                            infoLink,
                            buyLink,
                            pdfLink
                        )

                        imgList.add(imgInfo)

                        val adapter = BookRVAdapter(imgList, requireContext())
                        val lManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                        val imgRV = binding.recyclerShowImagineHome

                        imgRV.adapter = adapter
                        imgRV.layoutManager = lManager
                    }


                } catch (e: Exception) {
                    Log.v("imgResponseLog", e.message.toString())
                }

            },
            { error ->
                Log.v("imgLog", error.message.toString())
                Toast.makeText(
                    requireContext().applicationContext,
                    "No book found!",
                    Toast.LENGTH_SHORT
                ).show()
            })

        imgQueue.add(imgRequest)

    }
    private fun scientificUi() {
        getSciFromApi("Scientific story")
    }
    private fun getSciFromApi(query: String) {
        sciList = ArrayList()

        sciRequestQueue = Volley.newRequestQueue(requireContext())

        sciRequestQueue.cache.clear()

        val url = "$BASE_URL_HOME$query"

        val queue = Volley.newRequestQueue(requireContext())

        val sciRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                loadingPB.visibility = View.GONE
                try {

                    val itemsArr = response.getJSONArray("items")
                    for (i in 0 until itemsArr.length()) {
                        val itemsObj = itemsArr.getJSONObject(i)
                        val volumeObj = itemsObj.getJSONObject("volumeInfo")
                        val title = volumeObj.optString("title")
                        val subtitle = volumeObj.optString("subtitle")
                        val authorsArray = volumeObj.getJSONArray("authors")
                        val publisher = volumeObj.optString("publisher")
                        val publishedDate = volumeObj.optString("publishedDate")
                        val description = volumeObj.optString("description")
                        val pageCount = volumeObj.optInt("pageCount")
                        val imageLinks = volumeObj.optJSONObject("imageLinks")
                        val thumbnail = imageLinks.optString("thumbnail")
                        val previewLink = volumeObj.optString("previewLink")
                        val infoLink = volumeObj.optString("infoLink")
                        val saleInfoObj = itemsObj.optJSONObject("saleInfo")
                        val buyLink = saleInfoObj.optString("buyLink")
                        val authorsArrayList: ArrayList<String> = ArrayList()
                        val pdfLink = itemsObj.optJSONObject("accessInfo")?.optString("pdfLink")
                        if (authorsArray.length() != 0) {
                            for (j in 0 until authorsArray.length()) {
                                authorsArrayList.add(authorsArray.optString(i))
                            }
                        }

                        val sciInfo = BookRvModel(
                            title,
                            subtitle,
                            authorsArrayList,
                            publisher,
                            publishedDate,
                            description,
                            pageCount,
                            thumbnail,
                            previewLink,
                            infoLink,
                            buyLink,
                            pdfLink
                        )

                        sciList.add(sciInfo)

                        val sciAdapter = BookRVAdapter(sciList, requireContext())
                        val lManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                        val sciRecyclerView = binding.recyclerShowScientificHome

                        sciRecyclerView.adapter = sciAdapter
                        sciRecyclerView.layoutManager = lManager
                    }
                } catch (e: Exception) {
                    Log.v("sciErrRequestLog", e.message.toString())
                }
            },
            { error ->
                Log.v("sciErrLog", error.message.toString())
            }
        )

        queue.add(sciRequest)
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