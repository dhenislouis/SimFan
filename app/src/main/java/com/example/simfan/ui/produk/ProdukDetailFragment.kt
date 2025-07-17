package com.example.simfan.ui.produk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simfan.databinding.ComponentProdukDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdukDetailFragment : Fragment() {

    private val viewModel: ProdukDetailViewModel by viewModels()
    private var _binding: ComponentProdukDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentProdukDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val promoId = arguments?.getString("promo_id") ?: ""
        val token = "Bearer your_jwt_token_here"

        viewModel.fetchPromoDetail(token, promoId)

        lifecycleScope.launchWhenStarted {
            viewModel.promoDetailState.collect { detail ->
                detail?.let {
                    binding.textTitle.text = it.title
                    binding.textDescription.text = it.description
                    binding.textDate.text = it.date
                    // Load image with Glide/Coil if needed
                }
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
