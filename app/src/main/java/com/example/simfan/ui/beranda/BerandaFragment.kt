package com.example.simfan.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simfan.data.repository.ArticleRepository
import com.example.simfan.data.repository.DepositoRepository
import com.example.simfan.data.repository.ProductRepository
import com.example.simfan.data.repository.PromoRepository
import com.example.simfan.databinding.FragmentBerandaBinding
import com.example.simfan.ui.produk.ProdukAdapter
import com.example.simfan.ui.promo.PromoAdapter
import com.example.simfan.ui.deposito.DepositoAdapter

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var depositoAdapter: DepositoAdapter
    private lateinit var promoAdapter: PromoAdapter

    private val viewModel: BerandaViewModel by viewModels {
        BerandaViewModelFactory(
            PromoRepository(simFanApiServiceInstance),
            ProductRepository(simFanApiServiceInstance),
            DepositoRepository(simFanApiServiceInstance),
            ArticleRepository(simFanApiServiceInstance)
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProdukRekomendasi()
        setupDepositoAktif()
        setupPromoBanner()

        val token = "Bearer your_jwt_token_here"
        viewModel.fetchBerandaData(token)

        lifecycleScope.launchWhenStarted {
            viewModel.promoState.collect { promos ->
                promoAdapter.submitList(promos)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.produkState.collect { products ->
                produkAdapter.submitList(products)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.depositoState.collect { depositos ->
                depositoAdapter.submitList(depositos)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.articleState.collect { articles ->
                articleAdapter.submitList(articles)
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

        // TODO: Fetch and observe data from ViewModel/API Go here
    }

    private fun setupProdukRekomendasi() {
        produkAdapter = ProdukAdapter()
        binding.includeBerandaProduk.recyclerProdukRekomendasi.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = produkAdapter
        }

        // TODO: Replace with real data from API Go
        produkAdapter.submitList(dummyProdukList())
    }

    private fun setupDepositoAktif() {
        depositoAdapter = DepositoAdapter()
        binding.includeBerandaDepositoAktif.recyclerDepositoAktif.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = depositoAdapter
        }

        // TODO: Replace with real data from API Go
        depositoAdapter.submitList(dummyDepositoList())
    }

    private fun setupPromoBanner() {
        promoAdapter = PromoAdapter()
        // If needed, attach to ViewPager2 or RecyclerView for promo banners
        // TODO: Add if your design requires carousel promo banners
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Dummy data for placeholder
    private fun dummyProdukList() = listOf(
        Produk("Deposito BRI", "Jakarta", "Rp10.000.000", "3 Bulan", "6%"),
        Produk("Deposito Mandiri", "Bandung", "Rp5.000.000", "6 Bulan", "5.5%"),
    )

    private fun dummyDepositoList() = listOf(
        Deposito("Deposito BCA", "6 Bulan", "Rp15.000.000", "Estimasi Rp500.000", "Aktif"),
        Deposito("Deposito CIMB", "12 Bulan", "Rp20.000.000", "Estimasi Rp1.200.000", "Aktif"),
    )
}

// Dummy data models (replace with your real models)
data class Produk(
    val name: String,
    val location: String,
    val minimum: String,
    val tenor: String,
    val bunga: String
)

data class Deposito(
    val name: String,
    val tenor: String,
    val amount: String,
    val estimate: String,
    val status: String
)
