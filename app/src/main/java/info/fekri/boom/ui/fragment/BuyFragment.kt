package info.fekri.boom.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.fekri.boom.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {
    private lateinit var binding: FragmentBuyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyBinding.inflate(layoutInflater)
        return binding.root
    }
}