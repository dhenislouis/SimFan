package com.example.simfan.ui.deposito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simfan.data.repository.DepositoRepository
import com.example.simfan.databinding.FragmentDepositoBinding

class DepositoFragment : Fragment() {

    private var _binding: FragmentDepositoBinding? = null
    private val binding get() = _binding!!

    private lateinit var depositoAdapter: DepositoAdapter

    private val viewModel: DepositoViewModel by viewModels {
        DepositoViewModelFactory(DepositoRepository(simFanApiServiceInstance))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
        setupDepositoList()

        val token = "Bearer your_jwt_token_here"
        viewModel.fetchDepositos(token)

        lifecycleScope.launchWhenStarted {
            viewModel.depositoState.collect { depositos ->
                depositoAdapter.submitList(depositos)
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

        // TODO: Fetch and observe deposito data from ViewModel / API Go
    }

    private fun setupTabs() {
        binding.includeDepositoTabs.tabSemua.setOnClickListener {
            // TODO: Filter semua deposito
        }
        binding.includeDepositoTabs.tabProses.setOnClickListener {
            // TODO: Filter deposito dalam proses
        }
        binding.includeDepositoTabs.tabAktif.setOnClickListener {
            // TODO: Filter deposito aktif
        }
        binding.includeDepositoTabs.tabLunas.setOnClickListener {
            // TODO: Filter deposito lunas
        }
    }

    private fun setupDepositoList() {
        depositoAdapter = DepositoAdapter()
        binding.recyclerDeposito.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = depositoAdapter
        }

        // TODO: Replace with real data from API Go
        depositoAdapter.submitList(dummyDepositoList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Dummy data for placeholder
    private fun dummyDepositoList() = listOf(
        DepositoItem("Deposito Mandiri", "6 Bulan", "Rp10.000.000", "Estimasi Rp300.000", "Aktif"),
        DepositoItem("Deposito BRI", "12 Bulan", "Rp20.000.000", "Estimasi Rp1.200.000", "Proses"),
        DepositoItem("Deposito CIMB", "3 Bulan", "Rp5.000.000", "Estimasi Rp90.000", "Lunas")
    )
}

// Dummy data model (replace with your real model)
data class DepositoItem(
    val name: String,
    val tenor: String,
    val amount: String,
    val estimate: String,
    val status: String
)
