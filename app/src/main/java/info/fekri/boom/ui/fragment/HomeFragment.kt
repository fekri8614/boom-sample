package info.fekri.boom.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajat.pdfviewer.PdfViewerActivity
import info.fekri.boom.databinding.FragmentHomeBinding
import info.fekri.boom.ux.adapter.HistoryAdapter
import info.fekri.boom.ux.adapter.ItemEvents
import info.fekri.boom.ux.adapter.PomesAdapter
import info.fekri.boom.ux.adapter.ScienceAdapter
import info.fekri.boom.ux.data.Book

class HomeFragment : Fragment(), ItemEvents {
    private lateinit var binding: FragmentHomeBinding

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
        initUi()
    }

    private fun initUi() {
        scienceUi()
        historyUi()
        poemsUi()
    }

    private fun poemsUi() {
        val data = arrayListOf<Book>(    /* getting update... */    )
        val poemsAdapter = PomesAdapter(data, this)
        binding.recyclerPhilosophy.adapter= poemsAdapter
        binding.recyclerPhilosophy.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun historyUi() {
        val data = arrayListOf<Book>(   /* getting update */    )
        val historyAdapter = HistoryAdapter(data, this)
        binding.recyclerHistory.adapter = historyAdapter
        binding.recyclerHistory.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun scienceUi() {
        val data = arrayListOf<Book>(    /* Everything will get ready :-) */    )
        val scienceAdapter = ScienceAdapter(data, this)
        binding.recycerScience.adapter = scienceAdapter
        binding.recycerScience.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    /*
    when clicked on items, open them.
    */
    override fun onItemClicked(book: Book) {
        startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context,
                book.pdfUrl,
                book.title,
                "",
                false
            )
        )
    }

}