package info.fekri.boom.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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
import info.fekri.boom.extra.KEY_SEND_PDF_FILE
import info.fekri.boom.ui.activity.ShowPdfActivity
import info.fekri.boom.ux.adapter.BookRVAdapter
import info.fekri.boom.ux.data.BookRvModel

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
    override fun onStart() {
        super.onStart()
        initUi()
    }
    private fun initUi() {
        topIconsEvent() /* on icons click event */
        openPdfEvent()
    }

    private fun openPdfEvent() {
        // pdf-show process
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