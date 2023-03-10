package info.fekri.boom.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajat.pdfviewer.PdfViewerActivity
import info.fekri.boom.databinding.FragmentHomeBinding
import info.fekri.boom.extra.MoreUiEvents
import info.fekri.boom.extra.PoemsUiEvents
import info.fekri.boom.extra.ScienceEvents
import info.fekri.boom.ux.adapter.*
import info.fekri.boom.ux.data.MoreUiData
import info.fekri.boom.ux.data.PoemsUiData
import info.fekri.boom.ux.data.ScienceData
import info.fekri.boom.ux.room.MyDatabase
import info.fekri.boom.ux.adapter.MoreAdapter
import info.fekri.boom.ux.adapter.PoemsAdapter
import info.fekri.boom.ux.adapter.ScienceAdapter

class HomeFragment() : Fragment() {
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

    }

}