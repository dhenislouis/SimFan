package com.example.simfan.ui.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simfan.data.repository.PromoRepository
import com.example.simfan.databinding.FragmentPromoBinding

class PromoFragment : Fragment() {

    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    private lateinit var promoAdapter: PromoAdapter

    private val viewModel: PromoViewModel by viewModels {
        PromoViewModelFactory(PromoRepository(simFanApiServiceInstance))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPromoList()

        val token = "Bearer your_jwt_token_here"
        viewModel.fetchPromos(token)

        lifecycleScope.launchWhenStarted {
            viewModel.promoState.collect { promos ->
                promoAdapter.submitList(promos)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loadingState.collect { isLoading ->
                binding.progressBar.isVisible = isLoading
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorState.collect { error ->
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }


        // TODO: Fetch and observe promo data from ViewModel / API Go
    }

    private fun setupPromoList() {
        promoAdapter = PromoAdapter()
        binding.recyclerPromo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = promoAdapter
        }

        // TODO: Replace with real data from API Go
        promoAdapter.submitList(dummyPromoList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Dummy data for placeholder
    private fun dummyPromoList() = listOf(
        PromoItem("Promo Akhir Tahun", "Cashback hingga 50% untuk transaksi tertentu.", "07 Juli 2025"),
        PromoItem("Promo Gajian", "Diskon admin top up saldo 100%.", "01 Juli 2025"),
        PromoItem("Promo Referral", "Dapatkan saldo Rp25.000 per teman yang join.", "30 Juni 2025")
    )
}

// Dummy data model (replace with your real model)
data class PromoItem(
    val title: String,
    val description: String,
    val date: String
)
