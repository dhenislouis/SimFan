package com.example.simfan.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentBerandaArtikelBinding

class BerandaArtikelFragment : Fragment() {

    private var _binding: ComponentBerandaArtikelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentBerandaArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Load article data from API or ViewModel
        // For example, setup RecyclerView for article list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
