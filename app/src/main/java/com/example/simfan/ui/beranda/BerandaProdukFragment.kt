package com.example.simfan.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentBerandaProdukBinding

class BerandaProdukFragment : Fragment() {

    private var _binding: ComponentBerandaProdukBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentBerandaProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Load product data from API or ViewModel
        // For example, setup RecyclerView for featured products
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
