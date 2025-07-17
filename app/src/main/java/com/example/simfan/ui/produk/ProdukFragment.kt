package com.example.simfan.ui.produk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simfan.data.repository.ProductRepository
import com.example.simfan.databinding.FragmentProdukBinding

class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null
    private val binding get() = _binding!!

    private lateinit var produkAdapter: ProdukAdapter

    private val viewModel: ProdukViewModel by viewModels {
        ProdukViewModelFactory(ProductRepository(simFanApiServiceInstance))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProdukList()
        setupTabs()
        setupSearch()

        val token = "Bearer your_jwt_token_here"
        viewModel.fetchProducts(token)

        lifecycleScope.launchWhenStarted {
            viewModel.produkState.collect { products ->
                produkAdapter.submitList(products)
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


        // TODO: Fetch and observe data from ViewModel / API Go
    }

    private fun setupProdukList() {
        produkAdapter = ProdukAdapter()
        binding.recyclerProduk.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = produkAdapter
        }

        // TODO: Replace with real data from API Go
        produkAdapter.submitList(dummyProdukList())
    }

    private fun setupTabs() {
        binding.includeProdukTabs.tabBilyetFisik.setOnClickListener {
            // TODO: Filter produk untuk Bilyet Fisik
        }
        binding.includeProdukTabs.tabEDeposito.setOnClickListener {
            // TODO: Filter produk untuk E-Deposito
        }
        binding.includeProdukTabs.tabBungaBulanan.setOnClickListener {
            // TODO: Filter produk untuk Bunga Bulanan
        }
    }

    private fun setupSearch() {
        binding.includeProdukSearch.editTextSearchProduk.addTextChangedListener { text ->
            // TODO: Implement search filter with text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Dummy data for placeholder
    private fun dummyProdukList() = listOf(
        ProdukItem("Deposito BRI", "Jakarta", "Rp10.000.000", "3 Bulan", "6%"),
        ProdukItem("Deposito Mandiri", "Bandung", "Rp5.000.000", "6 Bulan", "5.5%"),
        ProdukItem("Deposito CIMB", "Surabaya", "Rp7.500.000", "12 Bulan", "6.2%"),
    )
}

// Dummy data model (replace with your real model)
data class ProdukItem(
    val name: String,
    val location: String,
    val minimum: String,
    val tenor: String,
    val bunga: String
)
