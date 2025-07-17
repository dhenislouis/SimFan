package com.example.simfan.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simfan.databinding.ComponentProfileMenuCardBinding
import com.example.simfan.R

class ProfileBantuanFragment : Fragment() {

    private var _binding: ComponentProfileMenuCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentProfileMenuCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example menu item click handlers for Bantuan section
        binding.root.findViewById<View>(R.id.menuEditProfile).setOnClickListener {
            // Navigate to FAQ or Bantuan Detail
        }

        binding.root.findViewById<View>(R.id.menuSettings).setOnClickListener {
            // Navigate to Contact Us
        }

        binding.root.findViewById<View>(R.id.menuLogout).setOnClickListener {
            // Navigate to Terms and Conditions or show info
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
