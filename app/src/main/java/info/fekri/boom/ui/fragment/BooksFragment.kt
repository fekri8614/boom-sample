package info.fekri.boom.ui.fragment

import android.os.Bundle
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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import info.fekri.boom.databinding.FragmentBooksBinding
import info.fekri.boom.ux.adapter.BookRVAdapter
import info.fekri.boom.ux.data.BookRvModel

class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding

    // create variables
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var booksList: ArrayList<BookRvModel>
    private lateinit var loadingPB: ProgressBar
    private lateinit var searchEdt: AppCompatEditText
    private lateinit var searchBtn: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBooksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialize variables
        loadingPB = binding.idLoadingPB
        searchEdt = binding.idEdtSearchBooks
        searchBtn = binding.idBtnSearch

        searchBtn.setOnClickListener {
            loadingPB.visibility = View.VISIBLE
            // check if our searchEdt is empty or not
            if (searchEdt.text.toString().isEmpty())
                searchEdt.error = "Please, enter search query"
            else
            // if is not empty, get books data
                getBooksData(searchEdt.text.toString())
        }

    }

    private fun getBooksData(searchQuery: String) {
        // create a new arrayList
        booksList = ArrayList()

        // initialize mRequestQueue
        mRequestQueue = Volley.newRequestQueue(requireContext())

        // clear caches
        // this will be use when data is begin update
        mRequestQueue.cache.clear()

        // bellow url gets data as Json from API
        val url = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery"

        // we're creating new request queue
        val queue = Volley.newRequestQueue(requireContext())

        // create a variable for request and initialize it
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            loadingPB.visibility = View.GONE

            // inside response method we are extracting all json data
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

                    // save data after extracting
                    val bookInfo = BookRvModel(
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

                    // pass model in our array list
                    booksList.add(bookInfo)

                    // pass array list to the adapter
                    val adapter = BookRVAdapter(booksList, requireContext())

                    // add linear layout manager for recycler view
                    val layoutManager = GridLayoutManager(context, 3)
                    val mRecyclerView = binding.idRvBooks

                    // set layout manager and adapter
                    mRecyclerView.layoutManager = layoutManager
                    mRecyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                Log.v("boomLog", e.message.toString())
            }

        }, { error ->
            // show toast if something went wrong
            Log.v("boomLog", error.message.toString())
            Toast.makeText(
                requireContext().applicationContext,
                "No book found!",
                Toast.LENGTH_SHORT
            ).show()
        })

        // at last, add request to the queue
        queue.add(request)

    }

}