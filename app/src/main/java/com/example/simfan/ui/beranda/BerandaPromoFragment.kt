package com.example.simfan.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentBerandaPromoBinding

class BerandaPromoFragment : Fragment() {

    private var _binding: ComponentBerandaPromoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentBerandaPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Load promo data from API or ViewModel
        // For example, setup RecyclerView or ViewPager2 for promo carousel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
