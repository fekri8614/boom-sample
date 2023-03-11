package info.fekri.boom.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.fekri.boom.R
import info.fekri.boom.databinding.DialogShowEventHomeBinding
import info.fekri.boom.databinding.FragmentHomeBinding

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