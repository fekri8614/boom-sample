package info.fekri.boom.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import info.fekri.boom.R
import info.fekri.boom.databinding.FragmentSearchBinding
import info.fekri.boom.ui.activity.MainActivity
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.newCoroutineContext
import kotlin.coroutines.coroutineContext

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val btnSearch = view.findViewById<AppCompatImageButton>(R.id.btn_search)
        val edtSearch = view.findViewById<AppCompatEditText>(R.id.edt_search)
        btnSearch.setOnClickListener {
            val query = edtSearch.text.toString()
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, query)
            startActivity(intent)
        }

        return view
    }
}