package com.example.simfan.ui.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simfan.databinding.ComponentPromoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromoDetailFragment : Fragment() {

    private var _binding: ComponentPromoDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PromoDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentPromoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // TODO: Fetch promo detail data from arguments or ViewModel
//        // Populate promo image, title, description, and CTA button
//    }

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
