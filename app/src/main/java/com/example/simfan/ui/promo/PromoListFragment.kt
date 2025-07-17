package com.example.simfan.ui.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentPromoListBinding

class PromoListFragment : Fragment() {

    private var _binding: ComponentPromoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentPromoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Load promo list data from API or ViewModel
        // Setup RecyclerView for displaying promo cards
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
