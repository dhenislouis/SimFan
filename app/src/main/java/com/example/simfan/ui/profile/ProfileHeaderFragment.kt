package com.example.simfan.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentProfileHeaderBinding

class ProfileHeaderFragment : Fragment() {

    private var _binding: ComponentProfileHeaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentProfileHeaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Fetch user data from ViewModel or arguments
        binding.name.text = "Ayu Cantika"
        binding.email.text = "ayucantika@gmail.com"
        binding.referralBadge.text = "Kode Referal\\nRV07162"
        // Load avatar if available using Glide/Picasso/Coil here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
